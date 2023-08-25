package com.springmvc.financeassetspringmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author christinehsieh on 2023/8/25
 */
@Configuration
public class RestTemplateConfig {


    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

}
