package com.vaibhav.effigo.spring_batch.using.JSON.repository;

import com.vaibhav.effigo.spring_batch.using.JSON.entity.DiscountedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountedProductRepository extends JpaRepository<DiscountedProduct, Long> {

}
