/**
 * 
 */
package com.wangkeming.cms.web.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.wangkeming.cms.core.Page;
import com.wangkeming.cms.domain.Article;
import com.wangkeming.cms.domain.Category;
import com.wangkeming.cms.domain.Channel;
import com.wangkeming.cms.domain.User;
import com.wangkeming.cms.service.ArticleService;
import com.wangkeming.cms.service.ChannelCategoryService;
import com.wangkeming.cms.service.UserService;
import com.wangkeming.cms.utils.FileUtils;
import com.wangkeming.cms.web.Constant;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2018年1月10日 下午2:40:38
 */
@Controller
@RequestMapping("/my")
public class UserController {

	@Resource
	private ArticleService articleService;
	
	@Resource
	private ChannelCategoryService service;
	
	@RequestMapping({"/", "/index", "/home"})
	public String home(){
		return "user-space/home";
	}
	
	@RequestMapping({"/profile"})
	public String profile(){
		return "user-space/profile";
	}
	
	@RequestMapping("/blog/edit")
	public String edit(Model model,Integer id){
		List<Channel> list = service.getChannels();
		Article blog = new Article();
		if(id != null){
			blog = articleService.findArticleById(id);
		}
		model.addAttribute("blog", blog);
		model.addAttribute("channelList", list);
		return "user-space/blog_edit";
	}
	
	@RequestMapping("/echo")
	@ResponseBody
	public List<Channel> echo(){
		List<Channel> list = service.getChannels();
		return list;
	}
	
	@RequestMapping("/queryCategoryById")
	@ResponseBody
	public List<Category> queryCategoryById(Integer id){
		List<Category> list = service.getCategories(id);
		return list;
	}
	
	@RequestMapping("/blog/save")
	public String save(Integer id,String hots,HttpSession session,Model model,MultipartFile file,@Validated @ModelAttribute("blog") Article blog,BindingResult bindingResult) throws IllegalStateException, IOException{
		if (bindingResult.hasErrors()) {
			return "/user-space/blog_edit";
		}
		String string = FileUtils.upload(file);
		blog.setPicture(string);
		
		User user = (User) session.getAttribute(Constant.LOGIN_USER);
		blog.setAuthor(user);
		Date date = new Date();
		blog.setCreated(date);
		
		if("on".equals(hots)){
			blog.setHot(true);
		}else{
			blog.setHot(false);
		}
		if(id != null){
			blog.setId(id);
			articleService.update(blog);
		}else{
			articleService.saveArticle(blog);
		}
		return "redirect:/my/blogs";
	}
	
	@RequestMapping({"/blogs","/articles"})
	public String toList(HttpServletRequest request,Model model,HttpSession session,@RequestParam(defaultValue="1")Integer page){
		User user = (User) session.getAttribute(Constant.LOGIN_USER);
		Article conditions = new Article();
		conditions.setAuthor(user);
		List<Article> articleList = articleService.gets(conditions, null, null);
		model.addAttribute("blogs", articleList);
		return "/user-space/blog_list";
	}
	
	@RequestMapping("/lookImg")
	public void lookImg(String path,HttpServletRequest request,HttpServletResponse response){
		FileUtils.lookImg(path, request, response);
	}
}
