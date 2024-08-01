package com.atmosware.customerManagementSystem.business.abstracts;

import com.atmosware.customerManagementSystem.dtos.requests.CreateCustomerRequest;
import com.atmosware.customerManagementSystem.entities.Customer;

public interface MernisService {
    public boolean CheckIfRealPerson(CreateCustomerRequest customer);
}
