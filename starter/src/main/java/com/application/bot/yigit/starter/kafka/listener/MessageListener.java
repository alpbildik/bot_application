package com.application.bot.yigit.starter.kafka.listener;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.application.bot.yigit.starter.kafka.interfaces.IMessageStream;
import com.application.bot.yigit.starter.kafka.topics.Message;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageListener {
	
//	@StreamListener(IMessageStream.INPUT)
//	public void handleMessage(@Payload Message message) {
//		log.info("Recieved Message {}", message);
//	}
}
