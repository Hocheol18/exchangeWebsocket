package com.trading.upbit.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderBook {
    private String type;
    private String code;
    private Long timestamp;
    private Double totalAskSize;
    private Double totalBidSize;
    private List<OrderBookUnit> orderBookUnits;
    private String streamType;
}

