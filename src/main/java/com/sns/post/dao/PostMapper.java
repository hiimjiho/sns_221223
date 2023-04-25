package com.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostMapper {
	
	public List<Post> selectPostList();
	
	public int insertContent(
			@Param("userId") int userId,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public List<Post> selectPostListByUserId(int userId);
	
	public Post selectPostByPostId(int postId);
	
	public int deletePost(int postId, int userId);
	
}
