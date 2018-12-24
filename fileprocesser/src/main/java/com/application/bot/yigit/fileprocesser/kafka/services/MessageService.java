package com.application.bot.yigit.fileprocesser.kafka.services;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.application.bot.yigit.fileprocesser.kafka.interfaces.IMessageStream;
import com.application.bot.yigit.fileprocesser.kafka.topics.Message;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageService {
	private final IMessageStream messageStream;
	
	public MessageService(IMessageStream messageStream) {
		this.messageStream = messageStream;
	}


    public void sendMessage(final Message message) {
        log.info("Sending greetings {}", message);
        MessageChannel messageChannel = messageStream.outboundMessage();
        messageChannel.send(MessageBuilder
                .withPayload(message)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
