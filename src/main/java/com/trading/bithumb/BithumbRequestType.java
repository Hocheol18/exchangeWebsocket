package com.trading.bithumb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BithumbRequestType {
    TICKER("ticker"),
    TRADE("trade"),
    ORDERBOOK("orderbook");

    private final String value;
}
