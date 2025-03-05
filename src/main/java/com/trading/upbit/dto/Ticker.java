package com.trading.upbit.dto;

import lombok.Data;

@Data
public class Ticker {
    private String type;
    private String code;
    private Double openingPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double tradePrice;
    private Double prevClosingPrice;
    private String change;
    private Double changePrice;
    private Double signedChangePrice;
    private Double changeRate;
    private Double signedChangeRate;
    private Double tradeVolume;
    private Double accTradeVolume;
    private Double accTradeVolume24h;
    private Double accTradePrice;
    private Double accTradePrice24h;
    private String tradeDate;
    private String tradeTime;
    private Long tradeTimestamp;
    private String askBid;
    private Long timestamp;
    private String streamType;
}
