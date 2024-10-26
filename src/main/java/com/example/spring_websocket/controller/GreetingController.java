package com.example.spring_websocket.controller;

import com.example.spring_websocket.model.Greetings;
import com.example.spring_websocket.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greetings greet(HelloMessage message){

        return  new Greetings("Hello : "+HtmlUtils.htmlEscape(message.getName()));

    }

}
