package com.trading.binance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trading.binance.BinanceRequestType;
import com.trading.upbit.dto.TickerData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BinanceWebSocketUtil {

    private final ObjectMapper camelObjectMapper;

    // 생성자 주입 + @Qualifier
    public BinanceWebSocketUtil(@Qualifier("binanceObjectMapper") ObjectMapper camelObjectMapper) {
        this.camelObjectMapper = camelObjectMapper;
    }

    public String makeBody(BinanceRequestType type, List<String> codes) throws JsonProcessingException {
        TickerData tickerData = createTickerData(type, codes);
        List<Object> dataList = Arrays.asList(tickerData);
        return convertToJson(dataList);
    }

    private TickerData createTickerData(BinanceRequestType type, List<String> codes) {
        TickerData tickerData = new TickerData();
        tickerData.setType(type.getValue());
        tickerData.setCodes(codes);
        tickerData.setOnlySnapshot(false);
        tickerData.setOnlyRealtime(true);
        return tickerData;
    }

    private String convertToJson(List<Object> dataList) throws JsonProcessingException {
        return camelObjectMapper.writeValueAsString(dataList);
    }
}