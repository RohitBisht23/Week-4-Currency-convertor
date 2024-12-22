package com.HomeWorkWeekFour.CurrencyConvertor.demo.Configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.rmi.ServerException;

@Configuration
public class AppConfig {

    @Value("${currency.client.base.url}")
    private String BASE_URL;
    @Bean
    public RestClient restClient(){
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError,(request, response) -> {
                    throw new ServerException("Server error occurred - "+response.getBody());
                }).build();
    }
}
