package com.product1.demo.controllers;


import com.product1.demo.model.SubCategory;
import com.product1.demo.services.SubCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(" http://localhost:5173")
public class SubCategoryController {
    private SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/subCategories")
    public List<SubCategory> getSubCategories(){
        return subCategoryService.getAllSubCategories();
    }

    @GetMapping("/subCategory/{id}")
    public SubCategory getSubCategory(@PathVariable("id") Long id){
        return subCategoryService.getSubCategoryById(id);
    }

    @PutMapping("/subCategory/{id}")
    public SubCategory updateSubCategory(@RequestBody() SubCategory subCategory, @PathVariable("id") Long id){
        return subCategoryService.save(subCategory);
    }

    @PostMapping("/subCategories")
    public SubCategory addNew(@RequestBody() SubCategory subCategory){
        return subCategoryService.save(subCategory);
    }

    @DeleteMapping("/subCategory/{id}")
    public void deleteSubCategory(@PathVariable("id") Long id){
        subCategoryService.deleteSubCategory(id);
    }


}
