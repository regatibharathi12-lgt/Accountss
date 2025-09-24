package com.dhathri.Accounts.mapper;

import com.dhathri.Accounts.dto.AccountsDto;

// dto to entity and entity to dto
public class AccountsMapper {
    public static AccountsDto mapToAccountsDto(com.dhathri.Accounts.entity.AccountsEntity accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static com.dhathri.Accounts.entity.AccountsEntity mapToAccountsEntity(AccountsDto accountsDto, com.dhathri.Accounts.entity.AccountsEntity accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }

}