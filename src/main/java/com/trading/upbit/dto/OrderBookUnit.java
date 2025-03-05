package com.trading.upbit.dto;

import lombok.Data;

@Data
public class OrderBookUnit {
    private Double askPrice;
    private Double bidPrice;
    private Double askSize;
    private Double bidSize;
}
