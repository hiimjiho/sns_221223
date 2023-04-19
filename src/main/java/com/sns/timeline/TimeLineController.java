package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/timeline")
public class TimeLineController {
	@Autowired
	private TimelineBO timelineBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@GetMapping("/timeline_view")
	public String timeLineView(
			HttpSession session,
			Model model
			) {
		
		//List<Post> postList = postBO.getPostList();
		//List<Comment> commentList = commentBO.getCommentList();
		//model.addAttribute("postList", postList);
		//model.addAttribute("commentList", commentList);
		List<CardView> cardList = timelineBO.generateCardList();
		List<CommentView> commentViewList = commentBO.generateCommentViewList(commentBO.); 
		model.addAttribute("cardList", cardList);
		model.addAttribute("commentViewList", commentViewList);		
		model.addAttribute("view", "timeline/timeline");
		return "template/layout";
	}
	
	
	
}
