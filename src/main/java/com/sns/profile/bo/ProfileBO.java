package com.sns.profile.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.profile.model.UserView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class ProfileBO {
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PostBO postBO;
	
	public UserView generateProfile(int userId, String userLoginId) {
		
		UserView userView = new UserView();
		
		User user = userBO.getUserByLoginId(userLoginId);
		
		List<Post> postList = postBO.getPostListByUserId(userId);
		
		userView.setPostList(postList);
		userView.setUser(user);
		
		return userView;
		
	}
}
