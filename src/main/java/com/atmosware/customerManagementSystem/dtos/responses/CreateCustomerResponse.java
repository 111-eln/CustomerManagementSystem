package com.atmosware.customerManagementSystem.dtos.responses;

import lombok.Data;

@Data
public class CreateCustomerResponse {
    private String name;
    private String surName;
    private String citizenNumber;
}
