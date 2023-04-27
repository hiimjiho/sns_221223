package com.sns.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserMapper;
import com.sns.user.model.User;

@Service
public class UserBO {
	@Autowired
	private UserMapper userMapper;
	
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
	
	public void getUserByUserId(int userId) {
		userMapper.selectUserByUserId(userId);
	}
	
	
}
