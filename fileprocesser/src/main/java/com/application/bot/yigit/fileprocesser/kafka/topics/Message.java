package com.application.bot.yigit.fileprocesser.kafka.topics;

import java.io.File;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Message {
	private String id;
	private String text;
	private File file;
	private Date time;
}
