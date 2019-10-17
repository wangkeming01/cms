package com.wangkeming.cms.service;

import java.util.List;

import com.wangkeming.cms.domain.Comment;

public interface CommentService {

	List<Comment> findCommentsById(Integer id);

	boolean saveComment(Comment comment);

}
