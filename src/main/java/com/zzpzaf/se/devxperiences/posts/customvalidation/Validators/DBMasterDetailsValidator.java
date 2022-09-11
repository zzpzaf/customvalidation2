package com.zzpzaf.se.devxperiences.posts.customvalidation.Validators;

import java.sql.SQLException;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzpzaf.se.devxperiences.posts.customvalidation.Repositories.VendorsRepo;

public class DBMasterDetailsValidator implements ConstraintValidator< DBMasterDetails, Object>{


    String fieldName1;
    String fieldName2;

    Integer vendorId;
    Integer categoryId;

    @Autowired
    VendorsRepo vendorsRepo;

    @Override
    public void initialize(DBMasterDetails constraintAnnotation) {
        this.fieldName1 = constraintAnnotation.fieldName1();
        this.fieldName2 = constraintAnnotation.fieldName2();
    }


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {


        Object field1Value = new BeanWrapperImpl(value).getPropertyValue(fieldName1);
        Object field2Value = new BeanWrapperImpl(value).getPropertyValue(fieldName2);

        if (field2Value == null || field1Value == null) {
            return false;
        }
        vendorId = (Integer) field1Value;
        categoryId = (Integer) field2Value;

        List<Integer> vendorCategories = null;
        try {
            vendorCategories = vendorsRepo.getVendorAllowedCategories(vendorId);
            //System.out.println(vendorCategories);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (vendorCategories == null || vendorCategories.size() <= 0) {
            return false;
        }


        return vendorCategories.contains(categoryId);
    }
    
}
