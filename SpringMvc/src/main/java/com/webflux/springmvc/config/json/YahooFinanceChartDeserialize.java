package com.webflux.springmvc.config.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.webflux.springmvc.dto.Stock;

import java.io.IOException;

/**
 * @author christinehsieh on 2023/8/18
 */
public class YahooFinanceChartDeserialize extends JsonDeserializer<Stock> {

    @Override
    public Stock deserialize(JsonParser p, DeserializationContext ctxt)
    throws IOException, JacksonException {

        JsonNode treeNode = p.getCodec().readTree(p);
        treeNode = treeNode.get("chart").get("result").get(0).get("meta");

        return new Stock(treeNode.get("symbol").asText(),
                         treeNode.get("regularMarketPrice").asInt(),
                         treeNode.get("currency").asText());

    }

}
