package com.atmosware.customerManagementSystem.dtos.requests;

import com.atmosware.customerManagementSystem.crossCuttingConcerns.ValidLong;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;



import java.time.LocalDateTime;
import java.util.Date;
@Data
public class CreateCustomerRequest {
    @NotBlank(message = "Name is not be null")
    @Size(min=3,max = 50,message = "Name is between 3 and 50 letters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
    private String name;
    @NotBlank(message = "SurName is not be null")
    @Size(min=2,max = 50)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Surname must contain only letters")
    private String surName;
//
//    private int age;
    @NotNull
//    @Size(min=11,max = 11)
   // @Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$",message = "CitizenNumber must contain only numbers and length is must 11.")
    @ValidLong(message = "CitizenNumber must be 11 digits and contain only numbers")
    private long citizenNumber;
    public int birth_Date;
    private boolean is_active;

}
