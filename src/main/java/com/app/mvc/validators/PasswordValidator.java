package com.app.mvc.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidatorImpl.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    String message() default "give some strong password" ;
    Class<?>[] groups() default {};
    String regex() default "^[a-zA-Z0-9!@#$%*_-]{6,}$";
    Class<? extends Payload>[] payload() default {};

}
