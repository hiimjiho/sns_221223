package com.sns.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.post.dao.PostMapper;

@Controller
public class testController {
	@Autowired
	PostMapper postMapper;
	
	
	@ResponseBody
	@RequestMapping("/test1")
	public String test1() {
		return "hello world";
	}
	
	@ResponseBody
	@RequestMapping("/test2")
	public Map<String, Object> test2(){
		Map<String, Object> map = new HashMap<>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/test3")
	public List<Map<String, Object>> test3(){
		return postMapper.selectPostList();
	}
}
