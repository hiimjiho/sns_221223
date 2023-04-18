package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	// 업로드 된 이미지가 저장될 경로
	public static final String FILE_UPLOAD_PATH = "C:\\Users\\user\\Documents\\박지호\\박지호\\6_spring_project\\sns\\workspace\\images";
	
	// input: MultipartFile(이미지 파일), loginId 이미지가 겹치게 하지 않기 위해서 loginId도 받아옴.
	// output: image path(String)
	
	public String saveFile(String loginId, MultipartFile file) {
		String directoryName = loginId + "_" + System.currentTimeMillis() + "/";
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			return null;
		}
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return "/images/" + directoryName + file.getOriginalFilename();
		
	}
	
}