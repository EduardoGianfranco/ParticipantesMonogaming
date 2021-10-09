package com.acme.demoapi.config;

import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestTemplateConfig {   //Clase para hacer integración con RestTemplate
                                    //RestTemplate es una librería estandar de Spring para realizar integraciones sincrónicas
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

}
