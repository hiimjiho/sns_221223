package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/timeline")
public class TimeLineController {
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/timeline_view")
	public String timeLineView(
			HttpSession session,
			Model model
			) {
		
		List<Post> postList = postBO.getPostList();
		model.addAttribute("postList", postList);
		model.addAttribute("view", "timeline/timeline");
		return "template/layout";
	}
	
	
	
}
