package com.vaibhav.effigo.spring_batch.using.JSON.services;


import com.vaibhav.effigo.spring_batch.using.JSON.entity.Product;
import com.vaibhav.effigo.spring_batch.using.JSON.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

   public List<Product> getAllProducts(){
       return productRepository.findAll();
   }

}
