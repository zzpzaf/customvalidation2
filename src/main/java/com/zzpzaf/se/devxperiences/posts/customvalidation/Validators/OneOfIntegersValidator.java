package com.zzpzaf.se.devxperiences.posts.customvalidation.Validators;

import java.util.Arrays;
import java.util.stream.IntStream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OneOfIntegersValidator implements ConstraintValidator<OneOfIntegers, Integer>{

    Integer[] arrayOfValues; 

    @Override
    public void initialize(OneOfIntegers constraintAnnotation) {
        int[] values = constraintAnnotation.Values();
        this.arrayOfValues = IntStream.of( values ).boxed().toArray( Integer[]::new );

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        //arrayOfValues = new Integer[] {2, 17, 33, 5, 28}; 
        return  Arrays.asList(this.arrayOfValues).contains(value);
    }

}

