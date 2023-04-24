package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeMapper;

@Service
public class LikeBO {
	
	@Autowired
	private LikeMapper likeMapper;
	
	public void likeToggle(int postId, int userId) {
		// 서버에서 좋아요 개수를 얻어와서 세어준다
		// 객체가 뭔지 잘몰라
		
		// int -> 자료형 int형 변수likeCount에 likeMapper 인터페이스에 접근해서 int형 값을 할당함.
		int likeCount = likeMapper.selectlikeCount(postId, userId);
		
		if(likeCount > 0) {
			likeMapper.deleteLike(postId, userId);
		} else {
			likeMapper.insertLike(postId, userId);
		}
			
	}

}
