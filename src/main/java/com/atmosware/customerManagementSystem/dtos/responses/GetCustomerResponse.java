package com.atmosware.customerManagementSystem.dtos.responses;

import lombok.Data;

@Data
public class GetCustomerResponse {
    private String name;
    private String surName;
    private String citizenNumber;
}
