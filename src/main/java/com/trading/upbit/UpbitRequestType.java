package com.trading.upbit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public enum UpbitRequestType {
    TICKER("ticker"),
    TRADE("trade"),
    ORDERBOOK("orderbook");

    private final String value;
}
