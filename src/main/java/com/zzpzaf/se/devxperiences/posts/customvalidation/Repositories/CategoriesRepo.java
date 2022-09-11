package com.zzpzaf.se.devxperiences.posts.customvalidation.Repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects.Category;
import com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects.CategoryDTO;

@Repository
public class CategoriesRepo {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String CATEGORIES_TABLE = "categories";


    public List<Category> getAllCategories() {

        List<Category> categoriesList = new ArrayList<>();
        try {
            categoriesList = jdbcTemplate.query("SELECT * FROM " + CATEGORIES_TABLE, 
            BeanPropertyRowMapper.newInstance(Category.class));
        } catch (Exception e) {
            return null;
        }
        return categoriesList;
    }

    public Category getCategoryById(Long id) {

        Category category = new Category();
        try {
            category = jdbcTemplate.queryForObject("SELECT * FROM " + CATEGORIES_TABLE + " WHERE categoryId=?",
                   BeanPropertyRowMapper.newInstance(Category.class), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        return category;
    }

    public Category createCategory(CategoryDTO dto) {

        Category category = new Category();

        category.setCategoryName(dto.getCategoryName());
        category.setCategoryUUID(UUID.randomUUID().toString());

        int status = 0;
        try {
            status = jdbcTemplate.update("INSERT INTO " + CATEGORIES_TABLE + " (" +
                "categoryName, " +
                "categoryUUID" +
                ")" +
                " VALUES(?,?)",
                new Object[]{category.getCategoryName(),
                             category.getCategoryUUID(),
                });            

        } catch (Exception e) {
            return null;
        }

        Category createdCategory = new Category();
        if(status != 0){
            createdCategory = getCategoryByUUID(category.getCategoryUUID());
        }
        return createdCategory;

    }



    public Category updateCategoryById(Long id, CategoryDTO dto) {
        
        Category category = null;

        category = getCategoryById(id);
        category.setCategoryName(dto.getCategoryName());

        try {
            jdbcTemplate.update("UPDATE " + CATEGORIES_TABLE + " SET " + 
                "categoryName=? " +
                "WHERE categoryId=?",
                new Object[]{category.getCategoryName(),
                    category.getCategoryId()
                });   
                category = getCategoryById(id);
        } catch (Exception e) {
            return null;
        }

        return category;

    }




    public Category deleteCategoryById(Long id) {
        
        Category category = null;
        try {
            category = getCategoryById(id);
            jdbcTemplate.update("DELETE FROM " + CATEGORIES_TABLE + " WHERE categoryId=?", id);
        } catch (Exception e) {
            return null;
        }
        return category;

    }



    private Category getCategoryByUUID(String uuid) {

        Category category = new Category();
        try {
            category = jdbcTemplate.queryForObject("SELECT * FROM " + CATEGORIES_TABLE + " WHERE categoryUUID=?",
                   BeanPropertyRowMapper.newInstance(Category.class), uuid);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
        return category;
    }

}
