package com.bancobase.bootcamp.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bancobase.bootcamp.dto.CustomerDTO;
import com.bancobase.bootcamp.dto.CustomerInfoDTO;
import com.bancobase.bootcamp.dto.request.PreCustomerInfo;
import com.bancobase.bootcamp.services.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customer Controller")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    @Operation(summary = "Get all the customers and filter by name")
    public ResponseEntity<List<CustomerInfoDTO>> getCustomers(@RequestParam String name) {
        List<CustomerInfoDTO> customers = customerService.filterCustomersByName(name);

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping()
    @Operation(summary = "Create a new customer")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody PreCustomerInfo information) {
        CustomerDTO savedCustomer = customerService.createCustomer(information);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "Get a customer by ID")
    public ResponseEntity<CustomerInfoDTO> getCustomerById(@PathVariable Long customerId) {
        CustomerInfoDTO customer = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
