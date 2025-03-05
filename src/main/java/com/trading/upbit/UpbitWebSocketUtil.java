package com.trading.upbit;

import com.trading.upbit.dto.FormatData;
import com.trading.upbit.dto.TicketData;
import com.trading.upbit.dto.TickerData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UpbitWebSocketUtil {
    private final ObjectMapper camelObjectMapper;

    public String makeBody(UpbitRequestType type, List<String> codes) throws JsonProcessingException {
        TicketData ticketData = createTicketData();
        TickerData tickerData = createTickerData(type, codes);
        FormatData formatData = createFormatData();

        List<Object> dataList = Arrays.asList(ticketData, tickerData, formatData);

        return convertToJson(dataList);
    }

    private TicketData createTicketData() {
        return new TicketData("upbit-websocket-client");
    }

    private TickerData createTickerData(UpbitRequestType type, List<String> codes) {
        TickerData tickerData = new TickerData();
        tickerData.setType(type.getValue());
        tickerData.setCodes(codes);
        tickerData.setOnlySnapshot(false);
        tickerData.setOnlyRealtime(true);
        return tickerData;
    }

    private FormatData createFormatData() {
        return new FormatData("DEFAULT");
    }

    private String convertToJson(List<Object> dataList) throws JsonProcessingException {
        return camelObjectMapper.writeValueAsString(dataList);
    }
}
