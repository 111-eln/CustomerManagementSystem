package com.atmosware.customerManagementSystem.api.controllers;

import com.atmosware.customerManagementSystem.business.abstracts.CustomerService;
import com.atmosware.customerManagementSystem.dtos.requests.CreateCustomerRequest;
import com.atmosware.customerManagementSystem.dtos.requests.DeleteCustomerRequest;
import com.atmosware.customerManagementSystem.dtos.requests.GetByCitizenNumberCustomerRequest;
import com.atmosware.customerManagementSystem.dtos.requests.UpdateCustomerRequest;
import com.atmosware.customerManagementSystem.dtos.responses.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private CustomerService customerService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCustomerResponse add(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) {

        return customerService.add(createCustomerRequest);

    }

    @PutMapping("/{customerCitizenNumber}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateCustomerResponse update(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) {
        return customerService.update(updateCustomerRequest);
    }

    @GetMapping("/{customerCitizenNumber}")
    @ResponseStatus(HttpStatus.OK)

    public GetByCitizenNumberCustomerResponse getByCitizenNumber(@RequestParam @Valid long citizenNumber) {
        return customerService.getByCitizenNumber(citizenNumber);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetCustomerResponse> getAll() {
        return customerService.getAll();
    }

    @DeleteMapping("/{customerCitizenNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam @Valid long citizenNumber) {
       customerService.delete(citizenNumber);
    }
}


