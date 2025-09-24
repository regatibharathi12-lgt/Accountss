package com.dhathri.Accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
    @NotEmpty(message = "name can not be empty or null")
    @Size(min=5 , max=30,message = "the size of the name should be in between 5 and 30")
    private String name;

    @NotEmpty(message = "Email address can not to be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Pattern(regexp="^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;
    private AccountsDto accounts;

}