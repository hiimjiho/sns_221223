package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostMapper;
import com.sns.post.model.Post;

@Service
public class PostBO {
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	public Post getPostByPostIdUserId(int postId, int userId) {
		return postMapper.selectPostByPostIdUserId(postId, userId);
	}
	
	public int addContent(int userId, String content, MultipartFile file) {
		String imagePath = null;
		
		if(file != null) {
			// 서버에 이미지 업로드 후 imagePath받아옴
			imagePath = fileManager.saveFile(imagePath, file);
		}
		
		return postMapper.insertContent(userId, content, imagePath);
	}
	
	public List<Post> getPostList(){
		return postMapper.selectPostList();
	}
	
	public List<Post> getPostListByUserId(int userId){
		return postMapper.selectPostListByUserId(userId);
	}
	
	
	public void deletePostByPostIdUserId(int postId, int userId) {
		// 글을 삭제할 때 이미지 ,내용, 좋아요, 댓글을 모두 삭제해주어야 한다.
		
		// 해당 글을 포스트 아이디로 가져온다.
		Post post = getPostByPostIdUserId(postId, userId);

		// 이미지 삭제
		fileManager.deleteFile(post.getImagePath());
		
		// 댓글 삭제
		commentBO.deleteCommentBypostId(postId);
		
		// 좋아요 삭제
		likeBO.deleteLikeByPostId(postId);
		
		// 내용 삭제
		postMapper.deletePostByPostIdUserId(postId, userId);
		}
	
	public Post getPost(int userId) {
		return postMapper.selectPost(userId);
	}
	
}
