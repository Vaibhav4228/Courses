package com.example.projectioninspringdatajpa.controller;

import com.example.projectioninspringdatajpa.dto.ProductDTO;
import com.example.projectioninspringdatajpa.entity.Product;
import com.example.projectioninspringdatajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAllProductsController() {
        return productService.getAllProducts();
    }

    @PostMapping("/insert")
    public Product insertProductInDb(@RequestBody Product product) {
        return productService.insertProductIntoDatabase(product);
    }

    @GetMapping("/find/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PutMapping("/update/{id}")
    public Product updateProductById(@PathVariable String id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public Product deleteProductById(@PathVariable String id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/projections")
    public List<ProductDTO> getProjections() {
        return productService.getProductWithRequiredAttributes();
    }
}
