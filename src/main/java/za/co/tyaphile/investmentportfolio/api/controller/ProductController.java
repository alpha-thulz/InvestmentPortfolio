package za.co.tyaphile.investmentportfolio.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.tyaphile.investmentportfolio.api.model.Product;
import za.co.tyaphile.investmentportfolio.service.ProductService;
import za.co.tyaphile.investmentportfolio.api.respond.ApiResponder;

@RestController
public class ProductController extends ApiResponder {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Requires product body with product information to be added as an investment product
     *
     * @param product - endpoint
     * @return ResponseEntity - Product information if investment product successfully added otherwise error
     */
    @PostMapping("/product")
    public ResponseEntity<Object> createInvestment(@RequestBody Product product) {
        String investorID = product.getInvestorID();
        String productName = product.getProductName();
        String productType = product.getProductType();
        double balance = product.getCurrentBalance();

        // Error handling conditions
        if (productType == null) {
            return getResponseError(HttpStatus.BAD_REQUEST, "Invalid investment plan, please choose between SAVINGS or RETIREMENT");
        } else if (investorID == null) {
            return getResponseError(HttpStatus.BAD_REQUEST, "You need to be signed on in order to properly identity you with your investment");
        } else if (balance <= 0) {
            return getResponseError(HttpStatus.BAD_REQUEST, "Cannot invest amount equal or less than R 0.00");
        }

        // No error, returning processed data
        return getResponse(HttpStatus.OK, productService.createInvestment(investorID, productName, productType, balance));
    }

    /**
     * EndPoint to return product by ID
     *
     * @param id - product ID
     * @return ResponseEntity - entity of the requested product information
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getInvestments(@PathVariable String id) {
        return getResponse(HttpStatus.OK, productService.getInvestment(id));
    }
}
