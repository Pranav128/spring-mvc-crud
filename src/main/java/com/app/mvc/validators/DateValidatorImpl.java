package com.app.mvc.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class DateValidatorImpl implements ConstraintValidator<DateValidator,LocalDate> {
    LocalDate toCompare;
    @Override
    public void initialize(DateValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.toCompare=LocalDate.now();
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if(value != null)
            return Period.between(value,toCompare).getYears() >= 18;
        else
            return false;
    }
}
