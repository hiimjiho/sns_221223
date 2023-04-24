package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.like.dao.LikeMapper;
import com.sns.like.model.Like;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired 
	private CommentBO commentBO;
	
	@Autowired
	private LikeMapper likeMapper;
	
	// 비로그인도 카드 리스트가 보여져야 하기 때문에 userId는 null 가능
	public List<CardView> generateCardList(Integer userId) {
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록을 가져온다.
		List<Post> postList = postBO.getPostList();
		
		// postList 반복문
		// => 1:1 post -> cardView => cardViewList에 넣는다.
		for(Post post : postList) {
			CardView card = new CardView();
			
			// 글
			card.setPost(post);
			
			// 글쓴이 정보
			// 
			User user = userBO.getUserById(post.getUserId());
			card.setUser(user);
			
			// 댓글들
			List<CommentView> commentViewList = commentBO.generateCommentViewList(post.getId());
			card.setCommentList(commentViewList);
			
			// 내가(로그인이 된 사람) 좋아요를 눌렀는지 여부
			if(userId == null) {
				// 
				card.setFilledLike(false);
			}else {
				// like 객체가 만들어지기 위해서 -> 셀렉트로 가져온다. 가져올 때는 postId, userId 
				// 좋아요한 거 불러오기
				Like like = likeMapper.selectLike(post.getId(), userId);
				// 만약에 가져온게 있다 하면-> IF문으로 처리
				if(like == null) {
					card.setFilledLike(false);
				}else {
					card.setFilledLike(true);
				}
				// 
			}
			
			// !!!!!!!!!!!!!!! 카드리스트에 채우기 !!
			cardViewList.add(card);
		}
		
		return cardViewList;
	}
}
