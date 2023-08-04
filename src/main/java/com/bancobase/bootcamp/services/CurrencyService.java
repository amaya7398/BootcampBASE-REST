package com.bancobase.bootcamp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancobase.bootcamp.dto.CurrencyDTO;
import com.bancobase.bootcamp.dto.response.ExchangeRateResponse;
import com.bancobase.bootcamp.dto.response.Symbol;
import com.bancobase.bootcamp.dto.response.SymbolsNameResponse;
import com.bancobase.bootcamp.http.APIExchangeRateClient;

@RestController
public class CurrencyService {
    @Autowired
    private APIExchangeRateClient api;

    @GetMapping("/currency")
    public List<CurrencyDTO> getCurrency() {
        List<CurrencyDTO> currencies = new ArrayList<>();
        ExchangeRateResponse exchangeRateResponse = api.getExchangeRate();
        SymbolsNameResponse symbolsNameResponse = api.getSymbolsName();

        Map<String, Double> exchangeRates = exchangeRateResponse.getRates();
        Map<String, Symbol> symbols = symbolsNameResponse.getSymbols();
        // Map<String, Symbol> symbols = api.getSymbolsName().getSymbols();

        for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
            currencies.add(
                    CurrencyDTO.getFromSchema(entry, symbols));
        }
        return currencies;
    }
}
