package com.bancobase.bootcamp.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancobase.bootcamp.dto.CurrencyDTO;
import com.bancobase.bootcamp.dto.response.ExchangeRateResponse;
import com.bancobase.bootcamp.dto.response.SymbolsNameResponse;
import com.bancobase.bootcamp.http.APIExchangeRateClient;

@Service
public class CurrencyService {
    @Autowired
    private APIExchangeRateClient api;

    public List<CurrencyDTO> getCurrencies() {
        ExchangeRateResponse exchangeRateResponse = api.getExchangeRate();
        SymbolsNameResponse symbolsNameResponse = api.getSymbolsName();


        return CurrencyDTO
                .mergeExchangeRateAndSymbolsNameResponses(
                        exchangeRateResponse, symbolsNameResponse);
    }
}
