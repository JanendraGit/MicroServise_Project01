package com.example.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OrderConfig {
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient InventoryWebClient() {
        return webClientBuilder().baseUrl("http://inventory").build();
    }

    @Bean
    public ModelMap modelMap(){
        return new ModelMap();
    }
}
