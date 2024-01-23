package za.co.tyaphile.investmentportfolio.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import za.co.tyaphile.investmentportfolio.api.repo.ProductType;

import java.util.UUID;

/**
 * Product Entity, used with JPA to automatically make connections to the Database
 */
@Entity
public class Product {
    @Id
    private String productID;
    String investorID, productName, productType;
    private double currentBalance;

    public Product() {}

    public Product(String investorID, String productName, String productType, double balance) {
        this.investorID = investorID;
        this.productID = UUID.randomUUID().toString();
        this.productName = productName;
        this.currentBalance = balance;
        setProductType(productType);
    }

    public Product(String investorID, String productID, String productName, String productType, double balance) {
        this(investorID, productName, productType, balance);
        this.productID = productID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getInvestorID() {
        return investorID;
    }

    public void setInvestorID(String investorID) {
        this.investorID = investorID;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        switch (productType.toUpperCase()) {
            case "SAVINGS":
                this.productType = ProductType.SAVINGS.getProductTye();
                break;
            case "RETIREMENT":
                this.productType = ProductType.RETIREMENT.getProductTye();
                break;
            default:
                this.productType = null;
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
}