package com.app.mvc.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class ConfirmFieldsImpl implements ConstraintValidator<ConfirmFields,Object> {
    String val1 ,val2 ;
    @Override
    public void initialize(ConfirmFields constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.val1=constraintAnnotation.match1();
        this.val2=constraintAnnotation.match2();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object a = new BeanWrapperImpl(value).getPropertyValue(val1);
        Object b = new BeanWrapperImpl(value).getPropertyValue(val2);

        if( a != null){
            return a.equals(b);
        }
        else {
            return (b == null);
        }
    }
}
