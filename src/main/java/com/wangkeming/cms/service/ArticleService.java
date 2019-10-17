package com.wangkeming.cms.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.wangkeming.cms.core.Page;
import com.wangkeming.cms.domain.Article;
import com.wangkeming.cms.domain.User;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年3月28日 下午4:48:47
 */

public interface ArticleService {

	/**
	 * 功能说明：<br>
	 * @param conditions
	 * @param page
	 * @param orders  排序，默认按创建时间倒排序
	 * @return
	 * List<Article>
	 */
	public abstract List<Article> gets(Article conditions, Page page, LinkedHashMap<String, Boolean> orders);

	public abstract boolean saveArticle(Article blog);

	public abstract Article findArticleById(int id);

	public abstract void insertHit(Integer id);

	public abstract void insertCommentNum(Integer id);

	public abstract Article selectPreArticle(Map<String, Object> map);

	public abstract boolean updateStatus(Integer id);

	public abstract void update(Article blog);

	

}