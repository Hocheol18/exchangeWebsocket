package com.trading.bybit;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class BybitTradeHandler extends TextWebSocketHandler {
    private final ObjectMapper obejctMapper;

    public BybitTradeHandler(@Qualifier("bybitSnakeObjectMapper") ObjectMapper objectMapper) {
        this.obejctMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        log.info("Bybit Websocket 연결됨: {}", session.getId());

        String subscribeMessage = "{\"op\": \"subscribe\", \"args\": [\"publicTrade.BTCUSDT\"], \"req_id\": \"" + UUID.randomUUID() + "\"}";
        session.sendMessage(new TextMessage(subscribeMessage));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            String payload = message.getPayload();
            log.info("바이비트 메세지 수신: {}", payload);
        } catch (Exception e) {
            log.info("데이터 파싱 오류, e");
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("바이비트 WebSocket 연결 종료: {}, 사유: {}", session.getId(), status.getReason());
    }
}
