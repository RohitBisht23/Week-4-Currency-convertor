package com.HomeWorkWeekFour.CurrencyConvertor.demo.Controller;

import com.HomeWorkWeekFour.CurrencyConvertor.demo.Clients.Imlp.CurrencyConcertorClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/currency")
public class CurrencyConvertor {
    private final CurrencyConcertorClientImpl currencyConverterClient;
    //http://localhost:8080/convertCurrency?fromCurrency=INR&toCurrency=USD&u
    //nits=500
    @GetMapping("/convertCurrency")
    public ResponseEntity<Double> getRate(@RequestParam String fromCurrency, @RequestParam String toCurrency, @RequestParam Double units){

        return ResponseEntity.ok(currencyConverterClient.convertCurrency(fromCurrency, toCurrency, units));
    }
}
