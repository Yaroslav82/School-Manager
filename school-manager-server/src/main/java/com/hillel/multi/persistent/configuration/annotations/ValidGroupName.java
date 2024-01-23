package com.hillel.multi.persistent.configuration.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = ValidGroupNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidGroupName {

    String message() default "неправильный формат";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
