package com.gi.cryptoChat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.gi.cryptoChat.messaging.SendMessageHandler;
import com.gi.cryptoChat.model.InstantChatMessage;

@RestController
public class MessagingController {

	@Autowired
	SendMessageHandler sender;

	Logger logger = LoggerFactory.getLogger(MessagingController.class);

	@MessageMapping("/chat.subscribe")
	@SendTo("/topic/reply")
	public void subscribe(@Payload String username) {
		sender.createExchange(username);
		logger.info("subscribed............" + username);
	}

	@MessageMapping("/chat.message")
	@SendTo("/topic/reply")
	public void sendMessage(@Payload InstantChatMessage payload) {
		logger.info("send message from " + payload.getSender() + " to " + payload.getReceiver());
		sender.sendMessageToOutgoingExchange(payload);
	}
}
