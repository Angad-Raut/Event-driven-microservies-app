package com.event.driven.projectx.command.api.repository;

import com.event.driven.projectx.command.api.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails,String> {

    @Query(value = "select * from product_details where product_id=:productId",nativeQuery = true)
    ProductDetails findByProductId(@Param("productId")String productId);
}
