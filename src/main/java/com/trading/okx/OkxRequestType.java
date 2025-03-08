package com.trading.okx;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OkxRequestType {

    TICKER("ticker"),
    TRADE("trade"),
    ORDERBOOK("orderbook");

    private final String value;
}
