package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.dao.PostMapper;
import com.sns.post.model.Post;

@Service
public class PostBO {
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
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
}
