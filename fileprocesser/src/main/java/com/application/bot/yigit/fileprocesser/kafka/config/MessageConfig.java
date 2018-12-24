package com.application.bot.yigit.fileprocesser.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.application.bot.yigit.fileprocesser.kafka.interfaces.IMessageStream;

@EnableBinding(IMessageStream.class)
public class MessageConfig {

}
