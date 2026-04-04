package com.example.AssesmentZorvyn.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinWordsValidator implements ConstraintValidator<MinWords, String> {

    private int minWords;

    @Override
    public void initialize(MinWords constraintAnnotation) {
        this.minWords = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        int wordCount = value.trim().split("\\s+").length;

        return wordCount >= minWords;
    }
}
