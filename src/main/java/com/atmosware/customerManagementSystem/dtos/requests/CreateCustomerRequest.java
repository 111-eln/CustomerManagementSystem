package com.atmosware.customerManagementSystem.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCustomerRequest {
    @NotBlank(message = "Name is not be blank")
    @Size(min=3,max = 50,message = "Name is between 3 and 50 letters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
    private String name;
    @NotBlank(message = "SurName is not be blank")
    @Size(min=2,max = 50)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Surname must contain only letters")
    private String surName;

    @NotNull
    @Pattern(regexp = "^[0-9]{11}$",message = "CitizenNumber must be 11 digits and contain only numbers")
    private String citizenNumber;
    public int birth_Date;
    private boolean is_active;

}
