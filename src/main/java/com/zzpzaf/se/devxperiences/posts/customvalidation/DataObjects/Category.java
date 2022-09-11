package com.zzpzaf.se.devxperiences.posts.customvalidation.DataObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "categoryUUID" })
public class Category {
    
    private int categoryId;
    private String categoryName;
    private String categoryUUID;

    public Category() {};

    public Category(int categoryId, String categoryName, String categoryUUID) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryUUID() {
        return categoryUUID;
    }

    public void setCategoryUUID(String categoryUUID) {
        this.categoryUUID = categoryUUID;
    }

    @Override
    public String toString() {
        return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryUUID="
                + categoryUUID + "]";
    }


 
    
}
