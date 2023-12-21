package com.reactive.springreactive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.reactive.springreactive.json.YahooFinanceChartDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author christinehsieh on 2023/8/18
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = YahooFinanceChartDeserialize.class)
public class Stock {

    public Stock(String symbol, String recentPrice, String currency){
        this.symbol = symbol;
        this.recentPrice = new BigDecimal(recentPrice);
        this.currency = currency;
    }

    private String symbol;

    @JsonProperty("regularMarketPrice")
    private BigDecimal recentPrice;

    private String currency;

}
