package com.webflux.springmvc.service;

import com.webflux.springmvc.dto.Stock;
import com.webflux.springmvc.feign.YahooFinanceFiegnClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author christinehsieh on 2023/8/18
 */
@Service
public class YahooFinanceService {

    @Autowired
    private YahooFinanceFiegnClient yahooFinance;

    public Stock searchSingle(String symbol) throws IOException {

        return yahooFinance.askSingle(symbol);
    }
}
