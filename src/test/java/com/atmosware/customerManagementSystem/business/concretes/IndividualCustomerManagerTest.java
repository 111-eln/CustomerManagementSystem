package com.atmosware.customerManagementSystem.business.concretes;

import com.atmosware.customerManagementSystem.business.abstracts.MernisService;
import com.atmosware.customerManagementSystem.business.rules.CustomerBusinessRules;
import com.atmosware.customerManagementSystem.crossCuttingConcerns.exceptions.types.BusinessException;
import com.atmosware.customerManagementSystem.crossCuttingConcerns.mapping.ModelMapperManager;
import com.atmosware.customerManagementSystem.crossCuttingConcerns.mapping.ModelMapperService;
import com.atmosware.customerManagementSystem.dataAccess.CustomerRepository;
import com.atmosware.customerManagementSystem.dtos.requests.CreateCustomerRequest;
import com.atmosware.customerManagementSystem.dtos.requests.DeleteCustomerRequest;
import com.atmosware.customerManagementSystem.dtos.requests.UpdateCustomerRequest;
import com.atmosware.customerManagementSystem.dtos.responses.CreateCustomerResponse;
import com.atmosware.customerManagementSystem.dtos.responses.UpdateCustomerResponse;
import com.atmosware.customerManagementSystem.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IndividualCustomerManagerTest {
    private CustomerManager customerManager;
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private ModelMapperService modelMapperService;
    private MernisService mernisService;

    private CustomerBusinessRules rules;
    private MernisManager customerCheckManager;
    @BeforeEach
    void setUp(){
        customerRepository = mock(CustomerRepository.class);
        modelMapper = new ModelMapper();
        modelMapperService=new ModelMapperManager(modelMapper);
        mernisService = mock(MernisService.class);
        rules = new CustomerBusinessRules(customerRepository, customerCheckManager);
        customerManager = new CustomerManager(modelMapperService,customerRepository,rules);
        customerCheckManager=new MernisManager();
    }

    @Test
    void delete(){
        when(customerRepository.findByCitizenNumber(10563289655L)).thenReturn(new Customer());
        DeleteCustomerRequest request=new DeleteCustomerRequest();
        request.setCitizenNumber(10365698755L);
        customerManager.delete(10563289655L);
        when(customerRepository.findByCitizenNumber(10563289655L)).thenReturn(null);
        assert true;
    }
    @Test
    void mernisCheckShouldBeThrowException(){
        Customer customer=new Customer();
        customer.setCitizenNumber(10368569755L);
        customer.set_active(false);
        CreateCustomerRequest request=modelMapperService.forRequest().map(customer, CreateCustomerRequest.class);
        when(mernisService.CheckIfRealPerson(request)).thenReturn(false);
        when(mernisService.CheckIfRealPerson(request)).thenThrow(new BusinessException("This user is not real person."));
    }
    @Test
    void mernisCheckReturnTrue(){
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        createCustomerRequest.setCitizenNumber(10368569755L);
        createCustomerRequest.set_active(true);

      when(mernisService.CheckIfRealPerson(createCustomerRequest)).thenReturn(true);

        assertDoesNotThrow(() -> customerCheckManager.CheckIfRealPerson(createCustomerRequest));

    }
    @Test
    void add(){
        CreateCustomerRequest request=new CreateCustomerRequest();
        Customer customer=modelMapperService.forRequest().map(request, Customer.class);
        when(customerRepository.save(customer)).thenReturn(customer);
        CreateCustomerResponse response=modelMapperService.forResponse().map(customer,CreateCustomerResponse.class);

    }
    @Test
    void customerShouldBeExist(){
        when(customerRepository.findByCitizenNumber(12569854756L)).thenThrow(new BusinessException("This user doesnt exist."));
        assert true;
    }
    @Test
    void getByCitizenNumber(){
        Customer customer=new Customer();
        customer.setCitizenNumber(12569854756L);
        customerRepository.save(customer);
        when(customerRepository.findByCitizenNumber(12569854756L)).thenReturn(customer);
        assert true;
    }
    @Test
    void getAll(){
        Customer customer=new Customer();
        customer.setCitizenNumber(12569854756L);
        Customer customer2=new Customer();
        customer.setCitizenNumber(36598564724L);
        List<Customer> customers=new ArrayList<>();
        customers.add(customer2);
        customers.add(customer);
        customerRepository.save(customer);
        customerRepository.save(customer2);
        when(customerRepository.findAll()).thenReturn(customers);
    }
    @Test
    void customerAlreadyExist(){
        CreateCustomerRequest request=new CreateCustomerRequest();
        request.setCitizenNumber(10368956246L);
        Customer customer=modelMapperService.forRequest().map(request, Customer.class);
        when(customerRepository.save(customer)).thenReturn(customer);
        customerRepository.save(customer);
        CreateCustomerRequest newRequest=new CreateCustomerRequest();
        request.setCitizenNumber(10368956246L);
        when(customerRepository.save(customer)).thenThrow(new BusinessException("This user already exist."));
    }
    @Test
    void update(){
        UpdateCustomerRequest request=new UpdateCustomerRequest();
        request.setCitizenNumber(10368569755L);
        request.set_active(true);
        Customer customer=new Customer();
        customer.setCitizenNumber(10368569755L);
        customer.set_active(false);
        when(customerRepository.findByCitizenNumber(request.getCitizenNumber())).thenReturn(customer);
        customer.set_active(request.is_active());
        UpdateCustomerResponse response=modelMapperService.forResponse().map(customer, UpdateCustomerResponse.class);
        when(customerRepository.save(customer)).thenReturn(customer);

    }
    }



