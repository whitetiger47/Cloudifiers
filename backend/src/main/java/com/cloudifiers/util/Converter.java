package com.cloudifiers.util;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class Converter {

	public static File converMultipartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertedFile;
	}

	public static String generateUniqueName(String name) {
		return System.currentTimeMillis() + "_" + name;
	}
}
