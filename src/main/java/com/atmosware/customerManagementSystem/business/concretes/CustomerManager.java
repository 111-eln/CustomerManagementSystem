package com.atmosware.customerManagementSystem.business.concretes;

import com.atmosware.customerManagementSystem.business.abstracts.CustomerService;
import com.atmosware.customerManagementSystem.business.rules.CustomerBusinessRules;
import com.atmosware.customerManagementSystem.crossCuttingConcerns.mapping.ModelMapperService;
import com.atmosware.customerManagementSystem.dataAccess.CustomerRepository;
import com.atmosware.customerManagementSystem.dtos.requests.CreateCustomerRequest;
import com.atmosware.customerManagementSystem.dtos.requests.DeleteCustomerRequest;
import com.atmosware.customerManagementSystem.dtos.requests.UpdateCustomerRequest;
import com.atmosware.customerManagementSystem.dtos.responses.*;
import com.atmosware.customerManagementSystem.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private final ModelMapperService modelMapperService;
    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules customerBusinessRules;
    @Override
    public CreateCustomerResponse add(CreateCustomerRequest request) {
        customerBusinessRules.mernisCheck(request);
        customerBusinessRules.customerAlreadyExist(request.getCitizenNumber());
        Customer customer=modelMapperService.forRequest().map(request, Customer.class);
        Customer dbCustomer=customerRepository.save(customer);
        CreateCustomerResponse response=modelMapperService.forResponse().map(dbCustomer,CreateCustomerResponse.class);
        return response;
    }

    @Override
    public List<GetCustomerResponse> getAll() {
        List<Customer> customers=customerRepository.findAll();
        List<GetCustomerResponse> customerResponses=customers.stream().map(customer -> modelMapperService.forResponse().map(customer, GetCustomerResponse.class)).toList();
        return customerResponses;
    }

    @Override
    public GetByCitizenNumberCustomerResponse getByCitizenNumber(long citizenNumber) {
        customerBusinessRules.customerShouldBeExist(citizenNumber);
        Customer customer=customerRepository.findByCitizenNumber(citizenNumber);
        System.out.println(customer);
        GetByCitizenNumberCustomerResponse response=modelMapperService.forResponse().map(customer, GetByCitizenNumberCustomerResponse.class);
        return response;
    }

    @Override
    public UpdateCustomerResponse update(UpdateCustomerRequest request) {
        Customer customer=customerRepository.findByCitizenNumber(request.getCitizenNumber());
        customerBusinessRules.mernisCheck(modelMapperService.forRequest().map(customer,CreateCustomerRequest.class));
        customerBusinessRules.customerShouldBeExist(request.getCitizenNumber());
        customerRepository.save(customer);
        return modelMapperService.forResponse().map(customer, UpdateCustomerResponse.class);
    }

    @Override
    public void delete(long citizenNumber) {
        customerBusinessRules.customerShouldBeExist(citizenNumber);
        Customer customer=customerRepository.findByCitizenNumber(citizenNumber);
        customerRepository.delete(customer);


    }
}
