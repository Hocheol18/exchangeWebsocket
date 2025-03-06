package com.trading.upbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;

@Slf4j
@Service

public class UpbitWebSocketService {
    private static final String upbitServerURL = "wss://api.upbit.com/websocket/v1";
    private final WebSocketClient webSocketClient;
    private final UpbitTradeHandler upbitTradeHandler;

    public UpbitWebSocketService(@Qualifier("upbitWebSocket") WebSocketClient webSocketClient, UpbitTradeHandler upbitTradeHandler) {
        this.webSocketClient = webSocketClient;
        this.upbitTradeHandler = upbitTradeHandler;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void connectToUpbit() {
        try {
            log.info("Upbit Websocket connecting...");
            // 각 핸들러를 사용하여 WebSocket 연결 시작
            // TODO : ORDERBOOK Ticker
            webSocketClient.doHandshake(upbitTradeHandler, upbitServerURL);
        } catch (Exception e) {
            log.error("업비트 WebSocket 연결 실패", e);
        }
    }
}
