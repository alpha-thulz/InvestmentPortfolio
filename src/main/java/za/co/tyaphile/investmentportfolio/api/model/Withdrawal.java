package za.co.tyaphile.investmentportfolio.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

/**
 * Withdrawal Entity, used with JPA to automatically make connections to the Database
 */
@Entity
public class Withdrawal {
    @Id
    private String withdrawalID;
    private String investorID, productID, withdrawResults;
    private double amount;

    public Withdrawal() {}

    public Withdrawal(String investorID, String productID, double amount) {
        this.withdrawalID = UUID.randomUUID().toString();
        this.investorID = investorID;
        this.productID = productID;
        this.amount = amount;
    }

    public String getWithdrawalID() {
        return withdrawalID;
    }

    public void setWithdrawalID(String withdrawalID) {
        this.withdrawalID = withdrawalID;
    }

    public String getInvestorID() {
        return investorID;
    }

    public void setInvestorID(String investorID) {
        this.investorID = investorID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getWithdrawResults() {
        return withdrawResults;
    }

    public void setWithdrawResults(String withdrawResults) {
        this.withdrawResults = withdrawResults;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
