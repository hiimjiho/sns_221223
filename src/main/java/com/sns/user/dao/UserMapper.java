package com.sns.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.user.model.User;

@Repository
public interface UserMapper {
	public User selectUserByLoginId(String loginId);
	
	public int getUser(
			@Param("loginId")String loginId,
			String password,
			String name,
			String email)
}
