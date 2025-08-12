package com.healthify.Healthyfy;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/sendMessage") // Listen for messages sent to "/app/sendMessage"
    @SendTo("/topic/messages") // Send the message to all subscribers of "/topic/messages"
    public ChatMessage sendMessage(ChatMessage message) {
        return message; // Return the message to be broadcasted
    }
}
