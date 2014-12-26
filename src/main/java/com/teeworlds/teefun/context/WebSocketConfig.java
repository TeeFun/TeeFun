/**
 *
 */
package com.teeworlds.teefun.context;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * Websocket configuration.
 *
 * @author Rajh
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	/**
	 * Configure STOMP over WebSocket end-points.
	 *
	 * @see com.blogspot.sunitkatkar.WebSocketBroadcastController.controllers.WebsocketBroadcastController
	 */
	@Override
	public void registerStompEndpoints(final StompEndpointRegistry registry) {
		registry.addEndpoint("/websocket/teefun").withSockJS();
	}

	/**
	 * Configure message broker options.
	 */
	@Override
	public void configureMessageBroker(final MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic/");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void configureClientInboundChannel(final ChannelRegistration channelRegistration) {
	}

	@Override
	public void configureClientOutboundChannel(final ChannelRegistration registration) {
		registration.taskExecutor().corePoolSize(4).maxPoolSize(10);
	}

	@Override
	public void configureWebSocketTransport(final WebSocketTransportRegistration registry) {
	}

	@Override
	public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
	}

	@Override
	public void addReturnValueHandlers(final List<HandlerMethodReturnValueHandler> returnValueHandlers) {
	}

	@Override
	public boolean configureMessageConverters(final List<MessageConverter> messageConverters) {
		return true;
	}

}
