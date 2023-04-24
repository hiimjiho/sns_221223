package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	
	//		GET:	/like?postId=13		@RequestParam
	//		GET or POST: /like/13	@PathVariable
	@RequestMapping("/like/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		// BO 호출 => BO안에서 like 여부 체크 
		likeBO.likeToggle(postId, userId);
		// 응답
		if(userId == null) {
			result.put("code", 500);
			result.put("result", "error");
			result.put("errorMessage", "error");
			return result;
		}
		result.put("code", 1);
		result.put("result", "성공");
		return result;
		
	}
}
