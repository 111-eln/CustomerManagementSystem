package com.atmosware.customerManagementSystem.dtos.responses;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UpdateCustomerResponse {
    private String name;
    private String surName;
    private String citizenNumber;
    private boolean is_active;
}
