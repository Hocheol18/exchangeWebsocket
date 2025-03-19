package com.trading.bithumb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;

@Service
@Slf4j
public class BithumbWebsocketService {
    private final static String bithumbServerUrl = "wss://ws-api.bithumb.com/websocket/v1";
    private final WebSocketClient webSocketClient;
    private final BithumbTradeHandler bithumbTradeHandler;

    public BithumbWebsocketService(@Qualifier("bithumbWebsocket") WebSocketClient webSocketClient, BithumbTradeHandler bithumbTradeHandler) {
        this.webSocketClient = webSocketClient;
        this.bithumbTradeHandler = bithumbTradeHandler;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void connectToBithumb() {
        log.info("Bithumb Websocket connecting...");
        try {
            webSocketClient.doHandshake(bithumbTradeHandler, bithumbServerUrl);
        } catch (Exception e) {
            log.error("빗썸 Websocket 연결 실패");
        }
    }


}
