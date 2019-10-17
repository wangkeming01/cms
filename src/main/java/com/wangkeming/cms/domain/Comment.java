package com.wangkeming.cms.domain;

import java.util.Date;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public class Comment {
	
	private Integer id;
	private User author;
	private Article article;
	private String content;
	private Date created;
	public Comment() {
	}
	public Comment(Integer id, User author, Article article, String content, Date created) {
		this.id = id;
		this.author = author;
		this.article = article;
		this.content = content;
		this.created = created;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated() {
		return created;
	}
	public void setDate(Date created) {
		this.created = created;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", author=" + author + ", article=" + article + ", content=" + content + ", created="
				+ created + "]";
	}
	
	
}
