package com.bancobase.bootcamp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bancobase.bootcamp.dto.CurrencyDTO;
import com.bancobase.bootcamp.services.CurrencyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/currency")
@Tag(name = "Currency Controller")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    @Operation(summary = "Get all the currencies vs MXN")
    public ResponseEntity<List<CurrencyDTO>> getCurrencies() {
        List<CurrencyDTO> currencies = currencyService.getCurrencies();
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }
}
