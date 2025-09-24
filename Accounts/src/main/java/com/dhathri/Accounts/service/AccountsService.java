package com.dhathri.Accounts.service;


import com.dhathri.Accounts.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface AccountsService {

    void createCustomer(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobilenumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);

}