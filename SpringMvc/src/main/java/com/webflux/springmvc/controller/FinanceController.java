package com.webflux.springmvc.controller;

import com.webflux.springmvc.dto.Stock;
import com.webflux.springmvc.service.YahooFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author christinehsieh on 2023/8/13
 */
@RestController
@RequestMapping("/finance/search")
public class FinanceController {

    @Autowired
    private YahooFinanceService yahooFinanceService;

    @GetMapping("/{symbol}")
    public Stock searchSingle(@PathVariable String symbol) throws IOException {

        return yahooFinanceService.searchSingle(symbol);
    }
    
}
