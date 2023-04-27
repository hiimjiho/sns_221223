package com.sns.profile.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.profile.bo.ProfileBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/profile")
@Controller
public class ProfileController {
	
	@Autowired
	private ProfileBO profileBO;
	
	@GetMapping("/profile_view")
	public String userProfile(Model model,	
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		// TODO null 체크 해주어야 함.
		if(userLoginId == null) {
			return "redirect:/user/sign_in_view";
		}
		
		UserView userView = profileBO.generateProfile(userId, userLoginId);
		model.addAttribute("userView", userView);
		model.addAttribute("view", "profile/profileIn");
		return "template/layout";
	}
}
