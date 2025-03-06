package com.trading.upbit;

import com.trading.upbit.UpbitRequestType;
import com.trading.upbit.dto.Trade;
import com.trading.upbit.UpbitWebSocketUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Slf4j
@Component
public class UpbitTradeHandler extends BinaryWebSocketHandler {
    private final ObjectMapper snakeObjectMapper;
    private final UpbitWebSocketUtil upbitWebSocketUtil;

    // 명시적 생성자에 @Qualifier 추가
    public UpbitTradeHandler(
            @Qualifier("upbitSnakeObjectMapper") ObjectMapper snakeObjectMapper,
            UpbitWebSocketUtil upbitWebSocketUtil) {
        this.snakeObjectMapper = snakeObjectMapper;
        this.upbitWebSocketUtil = upbitWebSocketUtil;
    }

    // 웹소캣 연결 이후 수행되는 로직
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        log.info("Trade WebSocket 연결됨: {}", session.getId());
        String requestBody = upbitWebSocketUtil.makeBody(
                UpbitRequestType.TRADE,
                Collections.singletonList("KRW-BTC")
        );
        session.sendMessage(new TextMessage(requestBody));
    }

    // 메세지 받는 로직
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        String payload = new String(message.getPayload().array(), StandardCharsets.UTF_8);
        Trade trade = snakeObjectMapper.readValue(payload, Trade.class);
        log.info("Trade 데이터 수신: {}", trade);

        //TODO DB 저장, 이벤트 발행
    }

    // 연결 닫힌 이후
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("Trade WebSocket 연결 종료: {}, 사유: {}", session.getId(), status.getReason());
    }
}

