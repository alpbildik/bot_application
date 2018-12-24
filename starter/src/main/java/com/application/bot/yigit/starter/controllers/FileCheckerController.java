package com.application.bot.yigit.starter.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.application.bot.yigit.starter.drive.DriveService;
import com.application.bot.yigit.starter.kafka.services.MessageService;
import com.application.bot.yigit.starter.kafka.topics.Message;
import com.application.bot.yigit.starter.util.StarterUtil;
import com.google.api.services.drive.model.File;


@RestController
public class FileCheckerController {
	private final MessageService messageService;
	private final DriveService driveService;

	public FileCheckerController(MessageService messageService, DriveService driveService) {
		this.messageService = messageService;
		this.driveService = driveService;
	}

	@GetMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<String>("test is ok", HttpStatus.OK);
	}

	@GetMapping("/messages")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void sendMessage(@RequestParam("text") String text) {
		Message message = Message.builder().text(text).id("1").time(new Date()).build();
		messageService.sendMessage(message);
	}

	@GetMapping("/downloadFiles")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<String> downloadFiles() {

		List<File> files = driveService.checkFiles();
		

		for (File file : files) {
			driveService.downloadExcelFile(file);
			Message message = Message.builder().text(file.getName()).file( ).id(file.getId()).time(new Date()).build();
			messageService.sendMessage(message);
		}
		
		return new ResponseEntity<String>(StarterUtil.createFileListJson(files), HttpStatus.OK);

	}
}
