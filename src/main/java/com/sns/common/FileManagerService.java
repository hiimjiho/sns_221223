package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	// 업로드 된 이미지가 저장될 경로
	public static final String FILE_UPLOAD_PATH = "F:\\박지호\\6_project\\sns\\workspace\\images/";
	
	// input: MultipartFile(이미지 파일), loginId 이미지가 겹치게 하지 않기 위해서 loginId도 받아옴.
	// output: image path(String)
	
	public String saveFile(String loginId, MultipartFile file) {
		// 파일 디렉토리(폴더) 예)	aaaa_1678687/sun.png
		String directoryName = loginId + "_" + System.currentTimeMillis() + "/";
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			return null;	// 폴더 만드는데 실패할 시 null로 리턴
		}
		
		// 파일 byte 단위로 업로드
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		// 파일 업로드가 성공했으면 이미지 url path를 리턴한다.
		// http:localhost:8080/images/aaaa_1675415153/sun.png
		return "/images/" + directoryName + file.getOriginalFilename();
		
	}
	
public void deleteFile(String imagePath) {	// imagePath:  /images/aaaa_1678687/sun.png
	
		
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
		// 이미지 삭제
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("[이미지 삭제] 이미지 삭제 실패. imagePath:{}", imagePath);
			}			
		}
		
		// 디렉토리(폴더) 삭제
		path = path.getParent();
		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("[이미지 삭제]" + imagePath);
			}
		}
		
	}
	
}
