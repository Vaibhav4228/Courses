package com.example.projectioninspringdatajpa.repository;

import com.example.projectioninspringdatajpa.entity.Product;
import com.example.projectioninspringdatajpa.projections.ProductProjections;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepo extends MongoRepository<Product, String> {

    List<ProductProjections> findAllBy();
}
