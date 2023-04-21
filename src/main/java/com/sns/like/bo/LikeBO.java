package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeMapper;

@Service
public class LikeBO {
	
	@Autowired
	private LikeMapper likeMapper;
	
	public void likeToggle(int postId, int userId) {
		
		int likeCount = likeMapper.selectlikeCount(postId, userId);
		
		if(likeCount > 0) {
			likeMapper.deleteLike(postId, userId);
		} else {
			likeMapper.insertLike(postId, userId);
		}
			
	}
}
