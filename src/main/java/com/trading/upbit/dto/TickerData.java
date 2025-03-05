package com.trading.upbit.dto;

import lombok.Data;
import java.util.List;

@Data
public class TickerData {
    private String type;
    private List<String> codes;
    private Boolean onlySnapshot;
    private Boolean onlyRealtime;
}
