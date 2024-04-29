package com.app.mvc.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class PasswordValidatorImpl implements ConstraintValidator<PasswordValidator,String> {

    List<String> weakpass;
    String regex;
    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.weakpass = Arrays.asList("123456","abcdefg","Pass@123","password");
        this.regex = constraintAnnotation.regex();
    }

    @Override
    public boolean isValid(String pass, ConstraintValidatorContext context) {

        return (pass !=null) && (!weakpass.contains(pass)) && (pass.matches(regex));
    }
}
