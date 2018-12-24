package com.application.bot.yigit.starter.kafka.interfaces;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface IMessageStream {
	String INPUT  = "message-in";
	String OUTPUT = "message-out";
	
	@Input(INPUT)
	SubscribableChannel inboundMessage();
	
	@Output(OUTPUT)
	MessageChannel outboundMessage();
}
