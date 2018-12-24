package com.application.bot.yigit.fileprocesser.kafka.listener;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.application.bot.yigit.fileprocesser.interfaces.IFileService;
import com.application.bot.yigit.fileprocesser.kafka.interfaces.IMessageStream;
import com.application.bot.yigit.fileprocesser.kafka.topics.Message;
import com.application.bot.yigit.fileprocesser.services.ExcelService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageListener {
	
	private final ExcelService excelService;
	
	public MessageListener(ExcelService excelService) {
		this.excelService = excelService;
	}
	
	@StreamListener(IMessageStream.INPUT)
	public void handleMessage(@Payload Message message) {
		log.info("Recieved Message {}", message);
		excelService.readFile(message.getText());
	}
}
