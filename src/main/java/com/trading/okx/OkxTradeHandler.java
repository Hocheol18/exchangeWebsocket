package com.trading.okx;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Slf4j
@Component
public class OkxTradeHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;

    public OkxTradeHandler(@Qualifier("okxSnakeObjectMapper") ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        log.info("오켁스 WebSocket 연결됨: {}", session.getId());


        String subscribeMessage = "{\"op\": \"subscribe\", \"args\": [{\"channel\": \"trades\", \"instId\": \"BTC-USDT\"}]}";
        session.sendMessage(new TextMessage(subscribeMessage));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 데이터 처리 로직...
        try {
            String payload = message.getPayload();
            log.info("오켁스 데이터 수신: {}", payload);
        } catch (Exception e) {
            log.error("데이터 파싱 오류", e);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("오켁스 WebSocket 연결 종료: {}, 사유: {}", session.getId(), status.getReason());
    }

}
