package com.springmvc.financeassetspringmvc.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.springmvc.financeassetspringmvc.config.YahooFinanceChartDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author christinehsieh on 2023/8/25
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = YahooFinanceChartDeserialize.class)
@ToString
public class Stock {

    private String symbol;

    private Integer recentPrice;

    private String currency;




}
