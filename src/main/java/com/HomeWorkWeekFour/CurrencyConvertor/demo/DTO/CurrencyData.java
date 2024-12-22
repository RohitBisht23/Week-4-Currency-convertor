package com.HomeWorkWeekFour.CurrencyConvertor.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurrencyData {
    private Map<String, Double> map;
}
