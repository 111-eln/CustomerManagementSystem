package com.atmosware.customerManagementSystem.dtos.responses;

import lombok.Data;

@Data
public class DeleteCustomerResponse {
    private String name;
    private String surName;
    private String citizenNumber;
}
