package com.dhathri.Accounts.mapper;


import com.dhathri.Accounts.dto.CustomerDto;
import com.dhathri.Accounts.entity.CustomerEntity;

//dto to entity and entity to dto
public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(CustomerEntity customerEntity, CustomerDto customerDto) {
        customerDto.setName(customerEntity.getName());
        customerDto.setEmail(customerEntity.getEmail());
        customerDto.setMobileNumber(customerEntity.getMobileNumber());
        return customerDto;
    }

    public static CustomerEntity mapToCustomerEntity(CustomerDto customerDto, CustomerEntity customerEntity) {
        customerEntity.setName(customerDto.getName());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setMobileNumber(customerDto.getMobileNumber());
        return customerEntity;
    }

}