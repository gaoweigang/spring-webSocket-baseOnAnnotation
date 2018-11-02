package com.gwg.demo.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer
{
    /*@Bean
    public ServerEndpointExporter serverEndpointExporter()
    {
        return new ServerEndpointExporter();
    }*/

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/sockjs/socketServer").setAllowedOrigins("*").withSockJS();
    }

}