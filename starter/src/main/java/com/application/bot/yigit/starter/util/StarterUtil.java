package com.application.bot.yigit.starter.util;

import java.util.List;

import org.json.JSONObject;

import com.google.api.services.drive.model.File;

public class StarterUtil {

	
	public static String createFileListJson(List<File> fileList) {
		
		JSONObject jsonObj = new JSONObject();
		
		if (fileList != null) {
			for (File file : fileList) {
				JSONObject jsonFile = new JSONObject();
				jsonFile.put("fileName", file.getName());
				jsonFile.put("id", file.getId());
				jsonObj.append("files", jsonFile);
			}
		}
		return jsonObj.toString();
	}
	
}
