package za.co.tyaphile.investmentportfolio.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.tyaphile.investmentportfolio.api.model.Investor;
import za.co.tyaphile.investmentportfolio.api.model.Product;
import za.co.tyaphile.investmentportfolio.api.model.Withdrawal;
import za.co.tyaphile.investmentportfolio.api.repo.ProductType;
import za.co.tyaphile.investmentportfolio.api.respond.ApiResponder;
import za.co.tyaphile.investmentportfolio.doa.InvestorRepo;
import za.co.tyaphile.investmentportfolio.doa.ProductRepo;
import za.co.tyaphile.investmentportfolio.doa.WithdrawalRepo;

@RestController
public class WithdrawalController extends ApiResponder {

    @Autowired
    private WithdrawalRepo withdrawalRepo;

    @Autowired
    private InvestorRepo investorRepo;

    @Autowired
    private ProductRepo productRepo;

    /**
     * Investor can make withdrawal
     *
     * Limitations are that investor cannot withdraw more than 90% of the amount
     * Investor should be at least 65 years to withdraw Retirement
     *
     * @param withdrawal information of the product and amount to withdraw
     * @return ResponseEntity
     */
    @PostMapping("/withdraw")
    public ResponseEntity<Object> makeWithdrawal(@RequestBody Withdrawal withdrawal) {
        System.out.println(withdrawal.getProductID());
        System.out.println(withdrawal.getInvestorID());
        System.out.println(withdrawal.getAmount());

        Product product = productRepo.findById(withdrawal.getProductID()).orElse(null);
        Investor investor = investorRepo.findById(withdrawal.getInvestorID()).orElse(null);

        if (product != null && investor != null) {
            int age = investor.getAge();
            double availableBalance = product.getCurrentBalance() * 0.9;

            Withdrawal withdraw = new Withdrawal(withdrawal.getInvestorID(), withdrawal.getProductID(), withdrawal.getAmount());

            if (age >= ProductType.valueOf(product.getProductType()).getWithdrawAgeLimit()) {
                if (withdrawal.getAmount() <= availableBalance) {
                    updateWithdrawInformation(withdraw, "Successful");
                    return getResponse(HttpStatus.OK, "Withdrawal successful");
                } else {
                    updateWithdrawInformation(withdraw, "Request above 90%");
                    return getResponseError(HttpStatus.CONFLICT, "Cannot withdraw more than 90% of the balance.");
                }
            } else {
                updateWithdrawInformation(withdraw, "Age below " + ProductType.RETIREMENT.getWithdrawAgeLimit());
                return getResponseError(HttpStatus.TOO_EARLY, "You need to be at least " + ProductType.RETIREMENT.getWithdrawAgeLimit() + " years old.");
            }
        }

        return getResponseError(HttpStatus.BAD_REQUEST, "Failed, invalid investor or product ID.");
    }

    /**
     * Update database with the withdrawal results, failed and successful withdrawal, for history logs
     * @param w - withdrawal information
     * @param result - String pertaining to the withdrawal success
     */
    private void updateWithdrawInformation(Withdrawal w, String result) {
        w.setWithdrawResults(result);
        withdrawalRepo.save(w);
    }
}

