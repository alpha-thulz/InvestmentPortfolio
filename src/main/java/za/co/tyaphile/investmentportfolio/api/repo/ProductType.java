package za.co.tyaphile.investmentportfolio.api.repo;

/**
 * Investment products and age withdrawal limits
 */
public enum ProductType {
    SAVINGS("SAVINGS", 0), // Savings investment, can be withdrawn at any age equal or greater than 0 years
    RETIREMENT("RETIREMENT", 65); // Retirement investment, can be withdrawn at any age equal or greater than 65 years

    final String productTye;
    final int withdrawAgeLimit;

    ProductType(String type, int limit) {
        this.productTye = type;
        this.withdrawAgeLimit = limit;
    }

    public String getProductTye() {
        return productTye;
    }

    public int getWithdrawAgeLimit() {
        return withdrawAgeLimit;
    }
}
