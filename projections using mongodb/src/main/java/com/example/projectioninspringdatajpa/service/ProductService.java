package com.example.projectioninspringdatajpa.service;

import com.example.projectioninspringdatajpa.dto.ProductDTO;
import com.example.projectioninspringdatajpa.entity.Product;
import com.example.projectioninspringdatajpa.mappers.ProductMapper;
import com.example.projectioninspringdatajpa.projections.ProductProjections;
import com.example.projectioninspringdatajpa.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductMapper productMapper;


    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }


    public Product insertProductIntoDatabase(Product product) {
        return productRepo.save(product);
    }


    public Product getProductById(String id) {
        Optional<Product> product = productRepo.findById(id);
        return product.orElse(null);
    }


    public Product updateProduct(String id, Product product) {
        Optional<Product> existingProduct = productRepo.findById(id);
        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();
            productToUpdate.setName(product.getName());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setQuantity(product.getQuantity());
            return productRepo.save(productToUpdate);
        }
        return null;
    }


    public Product deleteProduct(String id) {
        Optional<Product> product = productRepo.findById(id);
        product.ifPresent(productRepo::delete);
        return product.orElse(null);
    }


    public List<ProductDTO> getProductWithRequiredAttributes() {
        List<ProductProjections> projections = productRepo.findAllBy();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductProjections p : projections) {
            productDTOS.add(new ProductDTO(
                    p.getId(),
                    p.getName(),
                    p.getPrice(),
                    p.getQuantity()
            ));
        }
        return productDTOS;
    }
}
