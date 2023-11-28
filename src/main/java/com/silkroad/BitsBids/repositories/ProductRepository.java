package com.silkroad.BitsBids.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.silkroad.BitsBids.models.Product;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<List<Product>> findByProductType(String productType);

    Optional<List<Product>> findBySellerId(Long sellerId);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:query%")
    List<Product> searchProduct(@Param("query") String query);

}
