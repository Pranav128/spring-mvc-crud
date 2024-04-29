package com.app.mvc.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ConfirmFieldsImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface ConfirmFields {

    String message() default "filed don't match";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String match1() ;
    String match2() ;

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface list{
        ConfirmFields[] value();
    }

}
