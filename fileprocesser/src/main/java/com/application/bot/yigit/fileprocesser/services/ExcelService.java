package com.application.bot.yigit.fileprocesser.services;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.application.bot.yigit.fileprocesser.interfaces.IFileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExcelService implements IFileService {

	@Override
	public Object readFile(String filename) {
        try {
			Workbook workbook = WorkbookFactory.create(new File(filename));
			
			if (workbook != null) {
				for(Sheet sheet: workbook) {
					for(Row row : sheet) {
						System.out.println(row.toString());
					}
				}
			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			log.error("Error during file read name: {} error: {}", filename, e.getMessage());
		}
		
		return null;
	}

}
