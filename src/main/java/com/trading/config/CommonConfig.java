package com.trading.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class CommonConfig {

    // 기본 ObjectMapper - HTTP 메시지 변환 등에 사용됨
    @Bean
    @Primary
    public ObjectMapper defaultObjectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    // 업비트 ObjectMapper
    @Bean("upbitSnakeObjectMapper")
    public ObjectMapper upbitSnakeObjectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean("upbitCamelObjectMapper")
    public ObjectMapper upbitCamelObjectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    // 바이낸스 ObjectMapper
    @Bean("binanceSnakeObjectMapper")
    public ObjectMapper binanceSnakeObjectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean("binanceObjectMapper")
    public ObjectMapper binanceCamelObjectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    // 바이비트 ObjectMapper
    @Bean("bybitSnakeObjectMapper")
    public ObjectMapper bybitSnakeObjectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean("bybitObjectMapper")
    public ObjectMapper bybitObjectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    // WebSocket 클라이언트들
    @Bean("upbitWebSocket")
    public WebSocketClient upbitWebSocketClient() {
        return new StandardWebSocketClient();
    }

    @Bean("binanceWebSocket")
    public WebSocketClient binanceWebSocketClient() {
        return new StandardWebSocketClient();
    }

    @Bean("bybitWebSocket")
    public WebSocketClient bybitWebSocketClient() {
        return new StandardWebSocketClient();
    }
}