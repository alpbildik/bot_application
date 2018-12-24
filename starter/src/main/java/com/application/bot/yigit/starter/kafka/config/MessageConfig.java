package com.application.bot.yigit.starter.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.application.bot.yigit.starter.kafka.interfaces.IMessageStream;

@EnableBinding(IMessageStream.class)
public class MessageConfig {

}
