package com.example.AssesmentZorvyn.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinWordsValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MinWords {

    String message() default "Text must contain at least {value} words";

    int value();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
