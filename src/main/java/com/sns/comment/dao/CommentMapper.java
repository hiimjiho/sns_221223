package com.sns.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.model.Comment;

@Repository
public interface CommentMapper {
	public int insertComment(
			@Param("content") String content,
			@Param("userId") int userId,
			@Param("postId") int postId);
	
	public List<Comment> selectCommentList();
	
	public List<Comment> selectCommentListByPostId(int postId);
	
	public int deleteCommentByPostId(int postId);
	
	public int deleteComment(
			@Param("content") String content,
			@Param("userId") int userId,
			@Param("postId") int postId);
}
