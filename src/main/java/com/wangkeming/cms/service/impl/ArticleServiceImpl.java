/**
 * 
 */
package com.wangkeming.cms.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wangkeming.cms.core.Page;
import com.wangkeming.cms.dao.ArticleMapper;
import com.wangkeming.cms.domain.Article;
import com.wangkeming.cms.domain.User;
import com.wangkeming.cms.service.ArticleService;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年4月21日 下午9:06:07
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private ArticleMapper articleMapper;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Article> gets(Article conditions, Page page, LinkedHashMap<String, Boolean> orders) {
		List<Article> articles = articleMapper.selects(conditions, orders, page);
		if(page != null && page.getPageCount() == 0){
			int totalCount = articleMapper.count(conditions);
			page.setTotalCount(totalCount);
		}
		return articles;
	}

	@Override
	public boolean saveArticle(Article blog) {
		articleMapper.save(blog);
		return true;
	}

	@Override
	public Article findArticleById(int id) {
		return articleMapper.selectByPrimaryKey(id);
	}
	
	public boolean updateByKey(){
		articleMapper.updateByKey();
		return true;
	}

	@Override
	public void insertHit(Integer id) {
		articleMapper.increaseHit(id);
	}

	@Override
	public void insertCommentNum(Integer id) {
		articleMapper.insertCommentNum(id);
	}

	@Override
	public Article selectPreArticle(Map<String, Object> map) {
		return articleMapper.selectPreArticle(map);
	}

	@Override
	public boolean updateStatus(Integer id) {
		return articleMapper.updateStatusById(id) > 0 ? true : false;
	}

	@Override
	public void update(Article blog) {
		articleMapper.update(blog);
	}
	
}
