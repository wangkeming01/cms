/**
 * 
 */
package com.wangkeming.cms.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.wangkeming.cms.BaseTestCase;
import com.wangkeming.cms.core.Page;
import com.wangkeming.cms.domain.Article;
import com.wangkeming.cms.domain.Category;
import com.wangkeming.cms.domain.Channel;
import com.wangkeming.cms.service.ArticleService;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年4月21日 下午10:29:47
 */
public class ArticleServiceTest extends BaseTestCase {

	@Resource
	ArticleService articleService;

	/**
	 * Test method for {@link com.wangkeming.cms.service.ArticleService#gets(com.wangkeming.cms.domain.Article, com.wangkeming.cms.core.Page, java.util.LinkedHashMap)}.
	 */
	@Test
	public void testGets() {
		//------------------------------------
		Page _page = new Page(1, 30);
		Integer category = null, channel = null;
		//拼条件
		Article conditions = new Article();
		conditions.setDeleted(false);
		conditions.setStatus(1);

		//默认首页显示热门文章
		if(category == null && channel == null){
			conditions.setHot(true);
		}

		//如果频道或分类不为空，则显示分类或频道数据
		if(category != null){
			conditions.setCategory(new Category(category));
		}else if(channel != null){
			conditions.setChannel(new Channel(channel));
		}

		List<Article> articles = articleService.gets(conditions, _page, null);
		for (Article article : articles) {
			System.out.println(article.getTitle());
			if(article.getAuthor() != null){
				System.out.println("作者：" + article.getAuthor().getId() + "\t" + article.getAuthor().getNickname());
			}
			if(article.getChannel() != null){
				System.out.println("频道：" + article.getChannel().getId() + "\t" + article.getChannel().getName());
			}
			if(article.getCategory() != null){
				System.out.println("分类：" + article.getCategory().getId() + "\t" + article.getCategory().getName());
			}
			System.out.println("----------------------");
		}
	}

}
