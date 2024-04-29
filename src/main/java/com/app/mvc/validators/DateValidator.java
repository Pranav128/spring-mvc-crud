package com.app.mvc.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = DateValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface DateValidator {
    String message() default "Age must be 18+";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
