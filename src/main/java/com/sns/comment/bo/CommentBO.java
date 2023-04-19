package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentMapper;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;

@Service
public class CommentBO {
	@Autowired
	private CommentMapper commentMapper;
	
	public int addComment(String content, int userId, int postId) {
		return commentMapper.insertComment(content, userId, postId);
	}
	
	public List<Comment> getCommentList(){
		return commentMapper.selectCommentList();
	}
	
	
	// input:postId			output:가공된 댓글 리스트
	public List<CommentView> generateCommentViewList(int postId) {
		
		List<CommentView> commentViewList = new ArrayList<>();
		// 글에 해당하는 댓글들
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		// 반복문 Comment => CommentView => 결과 리스트에 담는다.
		for(Comment comment : commentList) {
			//	Comment => CommentView
			CommentView commentView = new CommentView();
			
			commentView.setComment(comment);
			commentViewList.add(commentView);
		}
		return commentViewList;
	}
}
