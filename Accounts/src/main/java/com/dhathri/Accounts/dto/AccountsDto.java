package com.dhathri.Accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
    @NotEmpty(message="Accountnumber should not be empty")
    @Pattern(regexp = "^$[0-9]{10}",message = "Account number should be in between 5-30")
    private Long accountNumber;

    @NotEmpty(message="accountType should not be empty")
    private String accountType;

    @NotEmpty(message = "branch address can not to be null")
    private String branchAddress;
    private AccountsDto accountsDto;

}