package com.healthapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.nio.charset.StandardCharsets;

@Configuration
public class ResponseMessageConfig {
    @Bean
    public HttpMessageConverter<String> messageConverter() {
         return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(StandardCharsets.UTF_8.name());
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }
}
