package com.product1.demo.controllers;

import com.product1.demo.model.Product;
import com.product1.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(" http://localhost:5173")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){

        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getProducts(){

        return productService.getProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long id){

        return productService.getProduct(id);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@RequestBody() Product product, @PathVariable("id") Long id){
        return productService.updateProduct(product);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addNew(@RequestBody() Product product){
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }
}
