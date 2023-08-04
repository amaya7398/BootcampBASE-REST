package com.bancobase.bootcamp.dto;

import java.util.Map;

import com.bancobase.bootcamp.dto.response.Symbol;

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

    public static CurrencyDTO getFromSchema(Map.Entry<String, Double> entry, Map<String, Symbol> symbols) {
        String symbol = entry.getKey(); // {"USD" : 20.0, "EUR" : 30.0, etc..}
        Double value = entry.getValue();
        String name = symbols.get(symbol).getDescription();

        return CurrencyDTO
                .builder()
                .name(name)
                .symbol(symbol)
                .value(value)
                .build();
    }

}
