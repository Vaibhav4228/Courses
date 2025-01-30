package com.vaibhav.effigo.spring_batch.using.JSON.repository;

import com.vaibhav.effigo.spring_batch.using.JSON.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {
}
