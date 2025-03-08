package com.trading.bybit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;

@Service
@Slf4j
public class BybitWebsocketService {
    private final static String bybitSpotServerUrl = "wss://stream.bybit.com/v5/public/spot";
    private final static String bybitFuturesServerUrl = "wss://stream.bybit.com/v5/public/linear";
    private final WebSocketClient webSocketClient;
    private final BybitTradeHandler bybitTradeHandler;

    public BybitWebsocketService(@Qualifier("bybitWebSocket") WebSocketClient webSocketClient, BybitTradeHandler bybitTradeHandler) {
        this.webSocketClient = webSocketClient;
        this.bybitTradeHandler = bybitTradeHandler;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void connectToBybit() {
        log.info("Bybit Websocket connecting...");
        try {
            log.info("Connecting to Bybit Websocket...");
            webSocketClient.doHandshake(bybitTradeHandler, bybitSpotServerUrl);
        } catch (Exception e) {
            log.error("바이비트 Websocket 연결 실패", e);
        }
    }
}
