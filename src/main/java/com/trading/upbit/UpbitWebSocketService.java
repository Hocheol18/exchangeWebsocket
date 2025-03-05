package com.trading.upbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;

import com.trading.upbit.UpbitTradeHandler;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpbitWebSocketService {
    private static final String upbitServerURL = "wss://api.upbit.com/websocket/v1";

    private final WebSocketClient webSocketClient;
    private final UpbitTradeHandler upbitTradeHandler;

    @EventListener(ApplicationReadyEvent.class)
    public void connectToUpbit() {
        try {

            // 각 핸들러를 사용하여 WebSocket 연결 시작
            // TODO : ORDERBOOK Ticker
            webSocketClient.doHandshake(upbitTradeHandler, upbitServerURL);
        } catch (Exception e) {
            log.error("업비트 WebSocket 연결 실패", e);
        }
    }
}
