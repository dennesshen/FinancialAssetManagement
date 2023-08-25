package com.springmvc.financeassetspringmvc.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.springmvc.financeassetspringmvc.dto.Stock;

import java.io.IOException;

/**
 * @author christinehsieh on 2023/8/25
 */
public class YahooFinanceChartDeserialize extends JsonDeserializer<Stock> {

    @Override
    public Stock deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException, JacksonException {

        JsonNode treeNode = p.getCodec().readTree(p);
        treeNode = treeNode.get("chart").get("result").get(0).get("meta");
        String symbol = treeNode.get("symbol").asText();
        int regularMarketPrice = treeNode.get("regularMarketPrice").asInt();
        String currency = treeNode.get("currency").asText();
        return new Stock(symbol, regularMarketPrice, currency);
    }
}
