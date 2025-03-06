package com.trading.binance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class binanceWebsocketService {
    private static final String binanceServerUrl = "wss://stream.binance.com:443";

    @EventListener(ApplicationReadyEvent.class)
    public void connectToBinance() {
        log.info("Binance Websocket connecting...");
    }
}
