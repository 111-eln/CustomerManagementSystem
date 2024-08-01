package com.atmosware.customerManagementSystem.crossCuttingConcerns;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LongValidator implements ConstraintValidator<ValidLong, Long> {

    @Override
    public void initialize(ValidLong constraintAnnotation) {
        // Initialize if needed
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Null değer geçerli kabul edilebilir; zorunlu değilse
        }

        String stringValue = String.valueOf(value);
        return stringValue.matches("^[0-9]{11}$"); // 11 haneli ve sadece sayılardan oluşan değerler için
    }
}