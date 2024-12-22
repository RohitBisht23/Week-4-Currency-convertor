package com.HomeWorkWeekFour.CurrencyConvertor.demo.Clients.Imlp;

import com.HomeWorkWeekFour.CurrencyConvertor.demo.Clients.CurrencyConvertorClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class CurrencyConcertorClientImpl implements CurrencyConvertorClient {
    private final RestClient restClient;

    Logger logger = LoggerFactory.getLogger(CurrencyConcertorClientImpl.class);

    @Override
    public Double convertCurrency(String fromCurrency, String toCurrency, Double units) {
        //Call api
        //http://localhost:8080/convertCurrency?fromCurrency=INR&toCurrency=USD&units=500
        //https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_bVhUk8RK004QjSE0Ho96kikZtdWfBaPIV9eS7zYx&currencies=INR&base_currency=USD
        Object currencyData = restClient.get()
                //.uri("/v1/latest?currencies={fromCurrency}&base_currency={toCurrency}",fromCurrency,toCurrency)
                .uri(uriBuilder -> uriBuilder
                        .path("v1/latest")
                        .queryParam("currencies", toCurrency)
                        .queryParam("base_currency", fromCurrency)
                        .build())
                .header("apikey","fca_live_bVhUk8RK004QjSE0Ho96kikZtdWfBaPIV9eS7zYx")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,(request, response) -> {
                    logger.error("Error - {} {} {}",response.getBody(),request.getURI(),request.getHeaders());
                    throw new HttpClientErrorException(response.getStatusCode(),response.getBody().toString());
                })
                .body(new ParameterizedTypeReference<>() {
                });
        return currencyData.getData().get(toCurrency)*units;
    }
}
