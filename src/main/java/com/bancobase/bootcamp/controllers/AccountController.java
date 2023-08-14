package com.bancobase.bootcamp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bancobase.bootcamp.dto.AccountDTO;
import com.bancobase.bootcamp.dto.CustomerInfoDTO;
import com.bancobase.bootcamp.dto.request.PreCustomerInfo;
import com.bancobase.bootcamp.repositories.AccountRepository;
import com.bancobase.bootcamp.schemas.AccountSchema;
import com.bancobase.bootcamp.schemas.CustomerSchema;
import com.bancobase.bootcamp.services.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Account Controller")
@CrossOrigin(origins = "*", methods = RequestMethod.GET)
public class AccountController {

    // Para pruebas unitarias lo mejor seria asignacion por constructor
    // AccCntlr(accServ) { this.accServ = accServ }
    @Autowired
    private AccountService accountService;

    @GetMapping("/{customerId}")
    @Operation(summary = "Get all the accounts of a customer with ID: customerId")
    public ResponseEntity<List<AccountDTO>> getAccountsByCustomerID(@PathVariable Long customerId) {
        List<AccountDTO> accounts = accountService.getAllAccountsByCustomerId(customerId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping("/{customerId}")
    @Operation(summary = " Create a new account of the customer")
    public ResponseEntity<List<AccountSchema>> createNewAccount(@RequestBody CustomerInfoDTO customerInfo) {
        // ERROR!
        // TODO: Fix this
        List<AccountSchema> accs = accountService.getAccountsByCustomerId(customerInfo.getCustomerId());
        CustomerSchema customer = new CustomerSchema();
        customer.setCustomerId(customerInfo.getCustomerId());
        customer.setName(customerInfo.getName());
        customer.setCurp(customerInfo.getCurp());
        customer.setGender(customerInfo.getGender());
        customer.setBirthdate(customerInfo.getBirthdate());
        customer.setAccounts(accs);

        List<AccountSchema> savedAccount = accountService.createAccount(customer);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

}
