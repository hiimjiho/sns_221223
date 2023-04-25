package com.sns.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("view", "user/signUp");
		return "template/layout";
	}
	
	@GetMapping("sign_in_view")
	public String signInView(Model model) {	
		model.addAttribute("view", "user/signIn");
		return "template/layout";
	}
	
	@RequestMapping("/sign_out")
	public String signOut(HttpSession session) {
		// 세션에 있는 모든 것을 비운다.
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		session.removeAttribute("userLoginId");
		
		// 로그인 화면 이동
		return "redirect:/user/sign_in_view";
	}
	
	@GetMapping("/profile_view")
	public String userProfile(Model model,
			
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		// TODO null 체크 해주어야 함.
		
		List<Post> postList = postBO.getPostListByUserId(userId);
		model.addAttribute("postList", postList);
		model.addAttribute("view", "user/profile");
		return "template/layout";
	}
	
	
}
