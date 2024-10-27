package com.healthify.Healthyfy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homecontroller {
    
    @GetMapping("/home")
    public String homepage() {
        // Return the name of the HTML file without the .html extension
        return "home"; // This will serve chatbot.html
    }
    @GetMapping("/chat")
    public String chatpage() {
        // Return the name of the HTML file without the .html extension
        return "chatbot"; // This will serve chatbot.html
    }
    @GetMapping("/testchat")
    public String chatpag1e() {
        // Return the name of the HTML file without the .html extension
        return "testbot"; // This will serve chatbot.html
    }
    
    @GetMapping("/appointment")
    public String chatpag1we() {
        // Return the name of the HTML file without the .html extension
        return "appointment"; // This will serve chatbot.html
    }
    @GetMapping("/chatroom")
    public String chatpag1wes() {
        // Return the name of the HTML file without the .html extension
        return "chatroom"; // This will serve chatbot.html
    }
    
    @GetMapping("/meeting")
    public String chatpag1wess() {
        // Return the name of the HTML file without the .html extension
        return "meeting"; // This will serve chatbot.html
    }
    
}
