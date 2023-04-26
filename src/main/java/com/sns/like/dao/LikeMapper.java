package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMapper {
	
	
//	public int selectlikeCount(
//			@Param("postId") int postId,
//			@Param("userId") int userId);
	
	public void insertLike(
			@Param("postId") int postId,
			@Param("userId") int userId); 
	
	public void deleteLike(
			@Param("postId")int postId,
			@Param("userId")int userId);
	
	// 내가 누른 좋아요 가져오기
//	public Like selectLike(
//			@Param("postId")int postId,
//			@Param("userId")int userId);
	
	// public int selectLikeCountByPostId(int postId);
	
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
	
	public void deleteLikeByPostId(int postId);
	
}
