package com.ZidioIntern.Controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ZidioIntern.Entity.ChatSupport;

@RestController
@RequestMapping("/support")
public class ChatSupportRestController {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChatSupportRestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/send")
    public ResponseEntity<ChatSupport> sendMessage(@RequestBody ChatSupport message) {
        message.setTimeStamp(LocalDateTime.now());

        // Broadcast to all WebSocket subscribers
        messagingTemplate.convertAndSend("/topic/message", message);

        return ResponseEntity.ok(message);
    }
}
