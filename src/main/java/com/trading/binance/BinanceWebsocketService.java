package com.trading.binance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;

@Service
@Slf4j
public class BinanceWebsocketService {
    private static final String binanceServerUrl = "wss://stream.binance.com:9443/ws";
    private final WebSocketClient webSocketClient;
    private final BinanceTradeHandler binanceTradeHandler;

    public BinanceWebsocketService(@Qualifier("binanceWebSocket") WebSocketClient webSocketClient, BinanceTradeHandler binanceTradeHandler) {
        this.webSocketClient = webSocketClient;
        this.binanceTradeHandler = binanceTradeHandler;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void connectToBinance() {
        log.info("Binance Websocket connecting...");
        try {
            log.info("Connecting to Binance Websocket...");
            webSocketClient.doHandshake(binanceTradeHandler, binanceServerUrl);
        } catch (Exception e) {
            log.error("바이낸스 Websocket 연결 실패", e);
        }
    }
}
