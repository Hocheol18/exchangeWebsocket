package com.trading.okx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;

@Service
@Slf4j
public class OkxWebsocketService {
    private final static String OkxServerUrl = "wss://ws.okx.com:8443/ws/v5/public";
    private final WebSocketClient webSocketClient;
    private final OkxTradeHandler okxTradeHandler;

    public OkxWebsocketService(@Qualifier("okxWebsocket") WebSocketClient webSocketClient, OkxTradeHandler okxTradeHandler) {
        this.webSocketClient = webSocketClient;
        this.okxTradeHandler = okxTradeHandler;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void connectToOkx() {
        log.info("Okx Websocket connecting...");
        try {
            log.info("Connecting to Okx Websocket...");
            webSocketClient.doHandshake(okxTradeHandler, OkxServerUrl);
        } catch (Exception e) {
            log.error("Okx Websocket 연결 실패", e);
        }
    }

}
