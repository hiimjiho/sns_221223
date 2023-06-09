package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/timeline")
public class TimeLineController {
	@Autowired
	private TimelineBO timelineBO;
	
	@GetMapping("/timeline_view")
	public String timeLineView(
			HttpSession session,
			Model model
			) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		//List<Post> postList = postBO.getPostList();
		//List<Comment> commentList = commentBO.getCommentList();
		//model.addAttribute("postList", postList);
		//model.addAttribute("commentList", commentList);
		
		List<CardView> cardList = timelineBO.generateCardList(userId);
		model.addAttribute("cardList", cardList);	
		model.addAttribute("view", "timeline/timeline");
		return "template/layout";
	}
	
	
	
}
