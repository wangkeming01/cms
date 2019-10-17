package com.wangkeming.cms.dao;

import java.util.List;

import com.wangkeming.cms.domain.Comment;

public interface CommentMapper {

	List<Comment> findCommentsById(Integer id);

	int saveComment(Comment comment);

}
