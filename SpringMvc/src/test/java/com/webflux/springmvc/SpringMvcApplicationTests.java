package com.webflux.springmvc;

import com.webflux.springmvc.service.YahooFinanceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class SpringMvcApplicationTests {


    @Autowired
    private YahooFinanceService yahooFinanceService;

    @Test
    void contextLoads() throws IOException {
        yahooFinanceService.searchSingle("2330.TW");
    }

}
