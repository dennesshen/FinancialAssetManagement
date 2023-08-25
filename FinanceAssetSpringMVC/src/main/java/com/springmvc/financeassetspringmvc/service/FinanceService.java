package com.springmvc.financeassetspringmvc.service;

import com.springmvc.financeassetspringmvc.dto.Stock;
import com.springmvc.financeassetspringmvc.fiegn.YahooFinanceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author christinehsieh on 2023/8/25
 */
@Service
public class FinanceService {

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private YahooFinanceApi yahooFinanceApi;

    public Stock searchSingle(String symbol) {

//        Stock stock = restTemplate.getForEntity("https://query1.finance.yahoo.com/v8/finance/chart/" + symbol,
//                                                Stock.class)
//                                  .getBody();

        Stock stock = yahooFinanceApi.askSingle(symbol);

        System.out.println(stock);

        return stock;
    }
}
