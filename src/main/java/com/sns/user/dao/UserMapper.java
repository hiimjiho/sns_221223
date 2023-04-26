package com.sns.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.user.model.User;

@Repository
public interface UserMapper {
	public User selectUserByLoginId(String loginId);
	
	public int insertUserMembership(
			@Param("loginId") String loginId,
			@Param("password") String password,
			@Param("name") String name,
			@Param("email") String email);
	
	public User selectUserByLoginIdPassword(
			@Param("loginId") String loginId,
			@Param("password") String password);
	
	public User selectUserById(int id);
	
	public List<User> selectUserList();
	
	public User selectUserByUserId(int userId);
}
