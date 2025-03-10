package com.trading.bithumb;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BithumbTradeHandler {
    private final ObjectMapper objectMapper;

    public BithumbTradeHandler(@Qualifier("bithumbSnakeObjectMapper") ObjectMapper objectMapper) {
        this.objectMapper = new ObjectMapper();
    }
}
