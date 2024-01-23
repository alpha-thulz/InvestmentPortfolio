package za.co.tyaphile.investmentportfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.tyaphile.investmentportfolio.api.model.Product;
import za.co.tyaphile.investmentportfolio.doa.ProductRepo;

import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

/**
 * Product service used to process investment data
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public Map<String, String> createInvestment(String investorID, String productName, String productType, double balance) {
        try {
            Product product = new Product(investorID, productName, productType, balance);
            repo.save(product);
            return Map.of("ProductID",product.getProductID(), "InvestorID", product.getInvestorID());
        } catch (NullPointerException e) {
            return Map.of("Error", e.getMessage());
        }
    }

    public List<Product> getInvestment(String id){
        Iterable<Product> products = repo.findAll();
        return StreamSupport.stream(products.spliterator(), false).filter(x -> x.getInvestorID().equals(id)).toList();
    }
}