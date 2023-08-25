package com.springmvc.financeassetspringmvc.controller;

import com.springmvc.financeassetspringmvc.dto.Stock;
import com.springmvc.financeassetspringmvc.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author christinehsieh on 2023/8/25
 */
@RestController
@RequestMapping("/finance/search")
public class FinanceController {

    @Autowired
    private FinanceService financeService;


    @GetMapping("/{symbol}")
    public Stock searchSingle(@PathVariable String symbol) {

        Stock information = financeService.searchSingle(symbol);
        return information;

    }


}
