/**
 * 
 */
package com.wangkeming.cms.web.controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.hql.internal.ast.tree.BooleanLiteralNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangkeming.cms.core.Page;
import com.wangkeming.cms.domain.Article;
import com.wangkeming.cms.domain.Category;
import com.wangkeming.cms.domain.Channel;
import com.wangkeming.cms.domain.Comment;
import com.wangkeming.cms.domain.Slide;
import com.wangkeming.cms.domain.User;
import com.wangkeming.cms.service.ArticleService;
import com.wangkeming.cms.service.ChannelCategoryService;
import com.wangkeming.cms.service.CommentService;
import com.wangkeming.cms.service.SlideService;
import com.wangkeming.cms.web.Constant;

/**
 * 说明:首页
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2018年1月10日 下午8:19:15
 */
@Controller
public class HomeController {

	@Resource
	private ArticleService articleService;
	
	@Resource
	private SlideService slideService;
	
	@Resource
	private CommentService commentService;
	
	@Resource
	private ChannelCategoryService channelCategoryService;
	
	
	@RequestMapping({"/", "/index", "/home"})
	public String home(
			@RequestParam(required = false) Integer channel, //频道
			@RequestParam(required = false) Integer category,//分类
			@RequestParam(defaultValue = "1") Integer page,//分类
			Model model){
		
		//------------------------------------
		Page _page = new Page(page, 30);
		List<Article> articles = null;
		
		//拼条件
		Article conditions = new Article();
		conditions.setDeleted(false);
		conditions.setStatus(1);

		//默认首页显示热门文章
		if(category == null && channel == null){
			conditions.setHot(true);
			
			//热门文章时显示幻灯片
			List<Slide> slides = slideService.getTops(5);
			model.addAttribute("slides", slides);
		}
		
		//如果频道或分类不为空，则显示分类或频道数据
		if(category != null){
			conditions.setCategory(new Category(category));
		}else if(channel != null){
			conditions.setChannel(new Channel(channel));
		}
		
		articles = articleService.gets(conditions, _page, null);
		model.addAttribute("articles", articles);
		

		//---------------右侧放10条最新文章---------------------
		Article lastArticlesConditions = new Article();
		lastArticlesConditions.setDeleted(false);
		lastArticlesConditions.setStatus(1);
		
		Page lastArticlesPage = new Page(1, 10);
		lastArticlesPage.setTotalCount(100);//设置了总记录数，可以节省统计查询，提高性能。
		
		List<Article> lastArticles = articleService.gets(lastArticlesConditions, lastArticlesPage, null);
		model.addAttribute("lastArticles", lastArticles);

		if(channel != null){
			model.addAttribute("channel", new Channel(channel));
		}
		model.addAttribute("category", category);
		return "home";
	}
	
	@RequestMapping({"/article/{id}","/blog/{id}"})
	public String toArticle(Model model,@PathVariable("id")Integer id,@RequestParam(required=false,defaultValue="1")Integer pageNum){
		Article article = articleService.findArticleById(id);
		if(article.getContent().length() > 500){
			article.setContent(article.getContent().trim().substring(0,500));
		}
		PageHelper.startPage(pageNum, 5);
		List<Comment> listComment = commentService.findCommentsById(id);
		PageInfo<Comment> pageInfo = new PageInfo<>(listComment);
		
		Article conditions = new Article();
		LinkedHashMap<String, Boolean> orders = new LinkedHashMap<>();
		orders.put("hits", false);
		List<Article> hitsList = articleService.gets(conditions,null,orders);
		/*for (Article article2 : hitsList) {
			article2.setTitle(article2.getTitle().substring(0,5));
		}*/
		
		LinkedHashMap<String, Boolean> orderses = new LinkedHashMap<>();
		orderses.put("comments", false);
		List<Article> commentsList = articleService.gets(conditions , null, orderses);
		/*for (Article article2 : commentsList) {
			article2.setTitle(article2.getTitle().substring(0,5));
		}*/
		
		articleService.insertHit(id);
		
		model.addAttribute("blog", article);
		model.addAttribute("page", pageInfo);
		model.addAttribute("hitsList", hitsList);
		model.addAttribute("commentList", commentsList);
		return "blog";
	}
	
	@RequestMapping("/my/comment/{id}")
	@ResponseBody
	public boolean Comment(@PathVariable("id")Integer id,HttpServletRequest request,String content){
		Comment comment = new Comment(null, Constant.getLoginUser(request), new Article(id), content, new Date());
		boolean saveComment = commentService.saveComment(comment);
		articleService.insertCommentNum(id);
		return saveComment;
	}
	
}
