package com.sns.user.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.user.dao.UserMapper;
import com.sns.user.model.User;
import com.sns.user.model.UserView;

@Service
public class UserBO {
	@Autowired
	private UserMapper userMapper;
	
	private User user;
	
	private Post post;
	
	private PostBO postBO;
	
	public List<User> getUserList(){
		return userMapper.selectUserList();
	}
	
	public User getUserByLoginId(String loginId) {
		return userMapper.selectUserByLoginId(loginId);
	}
	
	public int getUserMembership(String loginId, String password, String name, String email) {
		return userMapper.insertUserMembership(loginId, password, name, email);
	}
	
	public User getUserByLoginIdPassword(String loginId, String password) {
		return userMapper.selectUserByLoginIdPassword(loginId, password);
	}
	
	public User getUserById(int id) {
		return userMapper.selectUserById(id);
	}
	
	public List<Post> getPostList(){
		return postBO.getPostList();
	}
	
	public void getUserByUserId(int userId) {
		userMapper.selectUserByUserId(userId);
	}
	
	public List<UserView> getProfile() {
		
		List<UserView> getProfile = new ArrayList<>();
		
		List<User> userList = userMapper.selectUserList();
		
		
		
		for(User user : userList) {
			UserView userProfile = new UserView();
			userProfile.setUser(user);
			
			Post post = postBO.getPost(post.getUserId());
		
		}
		
		return getProfile;
		
		
	}
	
}
