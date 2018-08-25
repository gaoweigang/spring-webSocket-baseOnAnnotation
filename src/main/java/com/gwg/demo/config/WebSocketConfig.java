package com.gwg.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
@EnableWebSocket
@EnableWebMvc //启用SpringMvc
@ComponentScan("com.gwg") //扫描包
public class WebSocketConfig implements WebSocketConfigurer{

    @Bean
    public SpringWebSocketHandler springWebSocketHandler() {
        return new SpringWebSocketHandler();
    }

    @Bean
    public TextWebSocketHandler textWebSocketHandler(){
        return new SpringWebSocketHandler();
    }

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(textWebSocketHandler(),"/websocket/socketServer.do").addInterceptors(new SpringWebSocketHandlerInterceptor());
        registry.addHandler(textWebSocketHandler(), "/sockjs/socketServer.do").addInterceptors(new SpringWebSocketHandlerInterceptor()).withSockJS();
    }

}
