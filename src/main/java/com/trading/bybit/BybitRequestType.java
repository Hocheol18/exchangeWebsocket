package com.trading.bybit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BybitRequestType {

    TICKER("ticker"),
    TRADE("trade"),
    ORDERBOOK("orderbook");

    private final String value;
}
