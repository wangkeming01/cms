package com.wangkeming.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wangkeming.cms.dao.CommentMapper;
import com.wangkeming.cms.domain.Comment;
import com.wangkeming.cms.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentMapper commentMapper;
	
	@Override
	public List<Comment> findCommentsById(Integer id) {
		return commentMapper.findCommentsById(id);
	}

	@Override
	public boolean saveComment(Comment comment) {
		return commentMapper.saveComment(comment) > 0 ? true : false;
	}

}
