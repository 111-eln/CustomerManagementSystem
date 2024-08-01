package com.atmosware.customerManagementSystem.business.abstracts;

import com.atmosware.customerManagementSystem.dtos.requests.CreateCustomerRequest;
import com.atmosware.customerManagementSystem.dtos.requests.DeleteCustomerRequest;
import com.atmosware.customerManagementSystem.dtos.requests.GetByCitizenNumberCustomerRequest;
import com.atmosware.customerManagementSystem.dtos.requests.UpdateCustomerRequest;
import com.atmosware.customerManagementSystem.dtos.responses.*;

import java.util.List;

public interface CustomerService {
CreateCustomerResponse add(CreateCustomerRequest request);
List<GetCustomerResponse> getAll();
GetByCitizenNumberCustomerResponse getByCitizenNumber(String citizenNumber);
UpdateCustomerResponse update(UpdateCustomerRequest request);
void delete(String citizenNumber);
}
