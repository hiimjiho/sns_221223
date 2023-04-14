package com.sns.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class TimeLineController {
	@RequestMapping("timeline_view")
	public String timeLineView(Model model) {
		model.addAttribute("view", "timeline/timeLine");
		return "template/layout";
	}
}
