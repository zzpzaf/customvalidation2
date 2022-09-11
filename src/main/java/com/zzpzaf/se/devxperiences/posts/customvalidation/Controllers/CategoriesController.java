package com.zzpzaf.se.devxperiences.posts.customvalidation.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects.Category;
import com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects.CategoryDTO;
import com.zzpzaf.se.devxperiences.posts.customvalidation.Repositories.CategoriesRepo;


@RestController
public class CategoriesController {
    
    @Autowired
   CategoriesRepo categoriesRepo;


    @GetMapping(value = "/categories")
    public ResponseEntity<List<Category>> getAllVendors() {
        
        List<Category> returnCategoriesList = new ArrayList<>();

        try {
            returnCategoriesList = categoriesRepo.getAllCategories();
            if (returnCategoriesList.size() > 0) {
                return new ResponseEntity<>(returnCategoriesList, HttpStatus.OK); 
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getVendorById(@PathVariable("id") Long id) {

        Category category;
        category = categoriesRepo.getCategoryById(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDTO dto) {

        Category category = categoriesRepo.createCategory(dto);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable("id") Long id, @RequestBody CategoryDTO dto) {
        
        Category category = categoriesRepo.updateCategoryById(id, dto);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }



    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable("id") Long id) {

        Category category = categoriesRepo.deleteCategoryById(id);
        if (category != null ) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
