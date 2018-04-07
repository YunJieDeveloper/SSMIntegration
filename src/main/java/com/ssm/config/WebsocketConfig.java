package com.ssm.config;

import com.ssm.websocket.HandshakeInterceptor;
import com.ssm.websocket.WebsocketEndPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;
/**
 * WebScoket配置处理器
 */
@Component
@EnableWebMvc
@EnableWebSocket
public class WebsocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Resource
    WebsocketEndPoint handler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/ws").addInterceptors(new HandshakeInterceptor());
        registry.addHandler(handler, "/ws/sockjs").addInterceptors(new HandshakeInterceptor()).withSockJS();
    }
}
