package com.bancobase.bootcamp.dto;

import com.bancobase.bootcamp.dto.response.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CurrencyDTO {
    private String name;
    private String symbol;
    private Double value;

    public static List<CurrencyDTO> mergeExchangeRateAndSymbolsNameResponses(
            ExchangeRateResponse exchangeRateResponse,
            SymbolsNameResponse symbolsNameResponse
    ) {
        Map<String, Double> rates = exchangeRateResponse.getRates();
        Map<String, Symbol> symbols = symbolsNameResponse.getSymbols();

        return exchangeRateResponse
                .getRates()
                .keySet()
                .stream()
                .map(rate -> CurrencyDTO.builder()
                        .name(symbols.get(rate).getDescription())
                        .symbol(rate)
                        .value(rates.get(rate))
                        .build())
                .toList();
    }
}