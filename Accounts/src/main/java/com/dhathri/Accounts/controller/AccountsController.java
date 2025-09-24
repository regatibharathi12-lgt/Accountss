package com.dhathri.Accounts.controller;

import com.dhathri.Accounts.constants.AccountsConstants;
import com.dhathri.Accounts.dto.CustomerDto;
import com.dhathri.Accounts.dto.ResponseDto;
import com.dhathri.Accounts.service.AccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/accounts")
@AllArgsConstructor
@Validated

public class AccountsController {

    private AccountsService accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));

    }

    @GetMapping("/fetch")
    public  ResponseEntity<CustomerDto> fectchcustomerdetails(@RequestParam
                                                                  @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
                                                                  String mobilenumber){
    CustomerDto customerDto=accountsService.fetchAccount(mobilenumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto>updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated=accountsService.updateAccount(customerDto);
        if (isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
                  }
            }
            @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto>deleteAccountDetails(@RequestParam
                                                                       @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
                                                                       String mobilenumber){
        boolean isdeleted=accountsService.deleteAccount(mobilenumber);
        if (isdeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
        }
    }
}