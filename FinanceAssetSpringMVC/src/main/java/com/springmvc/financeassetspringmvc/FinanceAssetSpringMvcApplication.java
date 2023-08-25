package com.springmvc.financeassetspringmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FinanceAssetSpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceAssetSpringMvcApplication.class,
                              args);
    }

}
