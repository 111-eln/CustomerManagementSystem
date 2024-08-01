package com.atmosware.customerManagementSystem.dataAccess;

import com.atmosware.customerManagementSystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {


    Customer findByCitizenNumber(String citizenNumber);
}
