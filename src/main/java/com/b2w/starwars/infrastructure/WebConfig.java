package com.b2w.starwars.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

    public static final String API_TEMPLATE = "api";

    @Bean(name = API_TEMPLATE)
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
