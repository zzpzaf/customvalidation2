package com.zzpzaf.se.devxperiences.posts.customvalidation.Validators;

import java.sql.SQLException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzpzaf.se.devxperiences.posts.customvalidation.Repositories.VendorsRepo;

public class DBMasterDetailsValidator implements ConstraintValidator< DBMasterDetails, Object>{


    String fieldName1;
    String fieldName2;

    String field1message;
    String field2message;

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

        if (vendorsRepo.getVendorById(new Long(vendorId)) == null) {
            this.field1message = "Invalid " + this.fieldName1 + " : " + Integer.toString(vendorId);
            buildContext(context, this.fieldName1, this.field1message);
            return false;
        }

        boolean allowed = false;
        try {
            allowed = vendorsRepo.isVendorCategoryAllowed(vendorId, categoryId);
            if (!allowed) {
                this.field2message = this.fieldName2 + " : " + Integer.toString(categoryId) + " is not allowed for the " + this.fieldName1 + " : " + Integer.toString(vendorId);;
                buildContext(context, this.fieldName2, this.field2message);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allowed;
    }


    private void buildContext(ConstraintValidatorContext context, String fieldName, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
               .addPropertyNode(fieldName).addConstraintViolation();
    }

    
}
