package com.dhathri.Accounts.service;

import com.dhathri.Accounts.constants.AccountsConstants;
import com.dhathri.Accounts.dto.AccountsDto;
import com.dhathri.Accounts.entity.AccountsEntity;
import com.dhathri.Accounts.dto.CustomerDto;
import com.dhathri.Accounts.entity.CustomerEntity;
import com.dhathri.Accounts.exception.CustomerAlreadyExistsException;
import com.dhathri.Accounts.exception.ResourceNotFoundException;
import com.dhathri.Accounts.mapper.AccountsMapper;
import com.dhathri.Accounts.mapper.CustomerMapper;
import com.dhathri.Accounts.repository.AccountsRepository;
import com.dhathri.Accounts.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements AccountsService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    @Transactional
    public void createCustomer(CustomerDto customerDto) {

        CustomerEntity customer = CustomerMapper.mapToCustomerEntity(customerDto, new CustomerEntity());
        Optional<CustomerEntity> existsCustomer = customerRepository.findByMobileNumber(String.valueOf(customerDto.getMobileNumber()));
        if (existsCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with mobile number "
                    + customerDto.getMobileNumber() + " already exists");
        }
        CustomerEntity savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }


    private com.dhathri.Accounts.entity.AccountsEntity createNewAccount(CustomerEntity customer) {
        AccountsEntity newAccount = new AccountsEntity();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobilenumber) {
        CustomerEntity customer= customerRepository.findByMobileNumber(mobilenumber).orElseThrow(
                ()-> new ResourceNotFoundException("customerid","mobilenumber",mobilenumber)
        );


          AccountsEntity account= accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                    ()-> new ResourceNotFoundException("Account","CustomerId",customer.getCustomerId().toString())
            );
         CustomerDto customerDto= CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
         customerDto.setAccounts(AccountsMapper.mapToAccountsDto(account,new AccountsDto()));
        return customerDto;
    }

    @Override
    @Transactional
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated= false;
        AccountsDto accountDto=customerDto.getAccounts();
        if (accountDto!=null){
            AccountsEntity account=accountsRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    ()->new ResourceNotFoundException("Account","AccountNumber",accountDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccountsEntity(accountDto,account);
            account=accountsRepository.save(account);

            Long customerId=account.getCustomerId();
            CustomerEntity customer=customerRepository.findById(customerId).orElseThrow(
                    ()->new ResourceNotFoundException("Customer","CustomerId",customerId.toString())
            );
            CustomerMapper.mapToCustomerEntity(customerDto,customer);
            customerRepository.save(customer);
            isUpdated=true;
        }
        return isUpdated;
    }

    @Override
    @Transactional
    public boolean deleteAccount(String mobileNumber) {
        CustomerEntity customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

}