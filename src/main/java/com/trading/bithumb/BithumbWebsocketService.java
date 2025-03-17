package com.trading.bithumb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;

@Service
@Slf4j
public class BithumbWebsocketService {
    private final static String bithumbServerUrl = "wss://ws-api.bithumb.com/websocket/v1";
    private final WebSocketClient webSocketClient;
    
    public BithumbWebsocketService(@Qualifier("bithumbWebsocket") WebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }
}
