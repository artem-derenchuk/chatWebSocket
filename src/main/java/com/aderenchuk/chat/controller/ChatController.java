package com.aderenchuk.chat.controller;

import com.aderenchuk.chat.model.ChatInMessage;
import com.aderenchuk.chat.model.ChatOutMessage;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/guestchat")
    @SendTo("/topic/guestchats")
    public ChatOutMessage handleMessaging(ChatInMessage message) throws Exception{
        Thread.sleep(1000);

        return new ChatOutMessage(message.getMessage());

    }

    @MessageMapping("/guestupdate")
    @SendTo("/topic/guestupdates")
    public ChatOutMessage handleUsersIsTyping(ChatInMessage message) throws Exception {
        return new ChatOutMessage("Someone is typing...");
    }

    @MessageExceptionHandler
    @SendTo("/topic/errors")
    public ChatOutMessage handleException(Throwable exception) {
        ChatOutMessage myError = new ChatOutMessage("An error happened.");
        return myError;
    }
}
