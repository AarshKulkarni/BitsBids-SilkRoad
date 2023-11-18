package com.silkroad.BitsBids.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silkroad.BitsBids.models.Product;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    Optional<List<Product>> findByProductType(String productType);
}
