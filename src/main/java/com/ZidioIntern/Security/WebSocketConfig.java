//package com.ZidioIntern.Security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//import com.ZidioIntern.Service.chatSupportService;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
//	
//	@Autowired
//	private chatSupportService chatSupportServ;
//	
//	public WebSocketConfig(chatSupportService chatSupportServ) {
//		this.chatSupportServ = chatSupportServ;
//	}
//	
//	public void registerStompEndpoint(StompEndpointRegistry register){
//		chatSupportServ.logConnection("Register STOMP Endpoint");
//		register.addEndpoint("/ws-chat").setAllowedOriginPatterns("*").withSockJS();
//	}
//	
//	public void configureMessageBroker(MessageBrokerRegistry register) {
//		chatSupportServ.logConnection("Configuring Message Broker");
//		register.enableSimpleBroker("/topic");
//		register.setApplicationDestinationPrefixes("/app");
//	}
//}



package com.ZidioIntern.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import com.ZidioIntern.Service.chatSupportService;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private chatSupportService chatSupportServ;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        chatSupportServ.logConnection("Register STOMP Endpoint");
        registry.addEndpoint("/ws-chat").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        chatSupportServ.logConnection("Configuring Message Broker");
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}

