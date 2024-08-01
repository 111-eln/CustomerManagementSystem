package com.atmosware.customerManagementSystem.crossCuttingConcerns;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LongValidator implements ConstraintValidator<ValidLong, Long> {



    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;}

        String stringValue = String.valueOf(value);
        return stringValue.matches("^[0-9]{11}$");
    }
}