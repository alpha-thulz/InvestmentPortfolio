package za.co.tyaphile.investmentportfolio.doa;

import org.springframework.data.repository.CrudRepository;
import za.co.tyaphile.investmentportfolio.api.model.Product;

/**
 * Product repo extends CrudRepository, to make use of the CRUD database management
 */
public interface ProductRepo extends CrudRepository<Product, String> {
}
