package com.reactive.springreactive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.reactive.springreactive.json.YahooFinanceChartDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    private String symbol;

    @JsonProperty("regularMarketPrice")
    private Integer recentPrice;

    private String currency;

}
