package com.application.bot.yigit.starter.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import com.application.bot.yigit.starter.drive.DriveService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;

public class DriveUtils {

	public static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
	public static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	public static final String TOKENS_DIRECTORY_PATH = "tokens";
	public static final String WORKING_DIRECTORY_ID = "1ruNC3oUSZBgS1hK0A6dHtT2zirXA_W9S";
	public static final String WORKING_DIRECTORY_NAME = "botapp_folder";

	/**
	 * Global instance of the scopes required by this quickstart.
	 * If modifying these scopes, delete your previously saved tokens/ folder.
	 */
	private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	/**
	 * Creates an authorized Credential object.
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */
	public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
	    // Load client secrets.
	    InputStream in = DriveService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
	    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
	
	    // Build flow and trigger user authorization request.
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	            HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
	            .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
	            .setAccessType("offline")
	            .build();
	    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
	    return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

}