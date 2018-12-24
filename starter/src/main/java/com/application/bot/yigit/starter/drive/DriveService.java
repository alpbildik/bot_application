package com.application.bot.yigit.starter.drive;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.application.bot.yigit.starter.util.DriveUtils;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DriveService {

	public List<File> checkFiles() {
		NetHttpTransport HTTP_TRANSPORT;
		List<File> fileList = new ArrayList<>();
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			Drive service = new Drive.Builder(HTTP_TRANSPORT, DriveUtils.JSON_FACTORY,
					DriveUtils.getCredentials(HTTP_TRANSPORT)).setApplicationName(DriveUtils.APPLICATION_NAME).build();

			String query = String.format("'%s' in parents", DriveUtils.WORKING_DIRECTORY_ID);
			FileList result = service.files().list().setQ(query).execute();

			for (File file : result.getFiles()) {
				fileList.add(file);
			}
		} catch (GeneralSecurityException | IOException e) {
			log.error("Error checking files: {}", e.getMessage());
		}
		return fileList;
	}

	public void downloadExcelFile(File file) {
		NetHttpTransport HTTP_TRANSPORT;
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			Drive service = new Drive.Builder(HTTP_TRANSPORT, DriveUtils.JSON_FACTORY,
					DriveUtils.getCredentials(HTTP_TRANSPORT)).setApplicationName(DriveUtils.APPLICATION_NAME).build();

//			FileOutputStream outputStream = new FileOutputStream(file.getName());
			OutputStream outputStream = new OutputStream();
			
			service.files().export(file.getId(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
					.executeMediaAndDownloadTo(outputStream);

		} catch (GeneralSecurityException | IOException e) {
			log.error("Error during dowloading file {}", e.getMessage());
		}

	}
}
