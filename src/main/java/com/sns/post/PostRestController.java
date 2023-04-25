package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("content")String content,
			@RequestParam(value = "file", required=false) MultipartFile file,
			HttpSession session
			) {
		Integer userId = (Integer)session.getAttribute("userId");
		
		
		
		Map<String, Object> result = new HashMap<>();
		if(userId == null) {
			result.put("code", 500);
			result.put("result", "error");
			result.put("errorMessage", "error");
			return result;
		}
		postBO.addContent(userId, content, file);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
		
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(HttpSession session){
		
	
	}
	
	
}
