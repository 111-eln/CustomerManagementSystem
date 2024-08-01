package com.atmosware.customerManagementSystem.business.rules;

import com.atmosware.customerManagementSystem.business.concretes.MernisManager;
import com.atmosware.customerManagementSystem.crossCuttingConcerns.MessagesConstants;
import com.atmosware.customerManagementSystem.crossCuttingConcerns.exceptions.types.BusinessException;
import com.atmosware.customerManagementSystem.dataAccess.CustomerRepository;
import com.atmosware.customerManagementSystem.dtos.requests.CreateCustomerRequest;
import com.atmosware.customerManagementSystem.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
    private CustomerRepository customerRepository;
    private MernisManager customerCheckService;

    public void customerAlreadyExist(String customerCitiezenNumber){
        if(customerRepository.findByCitizenNumber(customerCitiezenNumber)!=null){
            throw new BusinessException(MessagesConstants.CustomerErrors.THIS_USER_ALREADY_EXIST);

        }
    }
    public void customerShouldBeExist(String customerCitiezenNumber){
        if(customerRepository.findByCitizenNumber(customerCitiezenNumber)==null){
            throw new BusinessException(MessagesConstants.CustomerErrors.THIS_USER_DOESNT_EXIST);

        }
    }
    public void mernisCheck(CreateCustomerRequest customer){
        if(!customerCheckService.CheckIfRealPerson(customer)){
            throw new BusinessException(MessagesConstants.CustomerErrors.THIS_USER_IS_NOT_REAL_PERSON);
        }

    }



}
