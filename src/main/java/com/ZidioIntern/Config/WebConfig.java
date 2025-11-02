package com.ZidioIntern.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	 @Override
     public void addCorsMappings(CorsRegistry registry) {
         registry.addMapping("/api/**")
                 .allowedOrigins("http://localhost:5174") // your frontend URL
                 .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                 .allowedHeaders("*")
                 .allowCredentials(false);
	 }
	 
	    public void registerStompEndpoints(StompEndpointRegistry registry) {
	        registry.addEndpoint("/support")
	                .setAllowedOrigins("http://localhost:5174")
	                .withSockJS();
	    }

	    public void configureMessageBroker(MessageBrokerRegistry registry) {
	        registry.enableSimpleBroker("/topic");
	        registry.setApplicationDestinationPrefixes("/app");
	    }
}
