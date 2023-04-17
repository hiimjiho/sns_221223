package com.sns.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.dao.PostMapper;

@Service
public class PostBO {
	@Autowired
	private PostMapper postMapper;
	
	public int addContent(int userId, String content, MultipartFile file) {
		String imagePath = null;
		return postMapper.insertContent(userId, content, imagePath);
	}
}
