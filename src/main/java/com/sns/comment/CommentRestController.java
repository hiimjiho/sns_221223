package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/comment")
public class CommentRestController {
	@Autowired 
	private CommentBO commentBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			HttpSession session,
			@RequestParam("postId") int postId,
			@RequestParam("content") String content
			){
		
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		if(userId == null) {
			result.put("code", 500);
			result.put("result", "error");
			result.put("errormessage", "error");
			return result;
		}
		
		commentBO.addComment(content, userId, postId);
		result.put("code", 1);
		result.put("result", "성공");
		
		return result;
	}
	
	@PostMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId") int postId,
			HttpSession session){
		
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		if(userId == null) {
			result.put("code", 500);
			result.put("result", "error");
			result.put("errorMessage", "error");
			return result;
		}
		commentBO.deleteComment(postId);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
}
