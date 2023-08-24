package com.webflux.springmvc.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.webflux.springmvc.config.json.YahooFinanceChartDeserialize;
import lombok.*;

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

    private Integer recentPrice;

    private String currency;

}
