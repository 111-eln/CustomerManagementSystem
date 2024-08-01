package com.atmosware.customerManagementSystem.crossCuttingConcerns.exceptions.handlers;

import com.atmosware.customerManagementSystem.crossCuttingConcerns.exceptions.problemDetails.BusinessProblemDetails;
import com.atmosware.customerManagementSystem.crossCuttingConcerns.exceptions.problemDetails.ValidationProblemDetails;
import com.atmosware.customerManagementSystem.crossCuttingConcerns.exceptions.types.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails handleBusinessException(BusinessException exception) {
        BusinessProblemDetails businessProblemDetails = new BusinessProblemDetails();
        businessProblemDetails.setDetail(exception.getMessage());
        return businessProblemDetails;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String,String> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().stream().map(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage())
        ).toList();



        return validationErrors;
    }
}
