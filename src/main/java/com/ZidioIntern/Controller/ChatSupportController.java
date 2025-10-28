package com.ZidioIntern.Controller;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;

import com.ZidioIntern.Entity.ChatSupport;

@Controller
//@RequestMapping("/support")
public class ChatSupportController {
	
	@MessageMapping("/SendMessage")
	@SendTo("/topic/message")
	
	public ChatSupport SendMessage(ChatSupport message){
		message.setTimeStamp(LocalDateTime.now());
		return message;
	}

}
