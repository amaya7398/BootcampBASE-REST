package com.bancobase.bootcamp.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bancobase.bootcamp.dto.response.ExchangeRateResponse;
import com.bancobase.bootcamp.dto.response.Symbol;
import com.bancobase.bootcamp.dto.response.SymbolsNameResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CurrencyDTO {
    private String name;
    private String symbol;
    private Double value;

    public static CurrencyDTO getFromSchema(String name, String symbol, Double value) {
        return CurrencyDTO
                .builder()
                .name(name)
                .symbol(symbol)
                .value(value)
                .build();
    }

    public static List<CurrencyDTO> mergeExchangeRateAndSymbolsNameResponses(
            ExchangeRateResponse exchangeRateResponse,
            SymbolsNameResponse symbolsNameResponse) {

        List<CurrencyDTO> currencies = new ArrayList<>();
        Map<String, Double> exchangeRates = exchangeRateResponse.getRates();
        Map<String, Symbol> symbols = symbolsNameResponse.getSymbols();

        for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) { // {"USD" : 20.0, "EUR" : 30.0, etc..}
            String symbol = entry.getKey(); // {"USD" : __ ,
            Double value = entry.getValue();// "__" : 30.0, }
            String name = symbols.get(symbol).getDescription();
            currencies.add(
                    CurrencyDTO.getFromSchema(name, symbol, value));
        }
        return currencies;
    }

}
