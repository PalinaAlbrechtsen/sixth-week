package com.example.sixthweek.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SearchBookValidator.class)
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSearchBook {

    String message() default "At least one field should be filled!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
