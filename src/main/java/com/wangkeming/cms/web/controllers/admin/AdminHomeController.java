/**
 * 
 */
package com.wangkeming.cms.web.controllers.admin;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.wangkeming.cms.core.Page;
import com.wangkeming.cms.domain.Article;
import com.wangkeming.cms.domain.Category;
import com.wangkeming.cms.domain.Channel;
import com.wangkeming.cms.service.ArticleService;
import com.wangkeming.cms.service.ChannelCategoryService;
import com.wangkeming.cms.web.controllers.PassportController;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年3月29日 下午6:54:11
 */
@Controller
@RequestMapping("/admin")
public class AdminHomeController {
	
	@Resource
	private ChannelCategoryService channelCategoryService;
	
	@Resource
	private ArticleService articleService;

	public static Logger log = LoggerFactory.getLogger(PassportController.class);
	
	@RequestMapping({"/", "/index"})
	public String home(){
		return "admin/home";
	}
	
	@RequestMapping("/categories")
	public String toChannelcategory(Model model){
		List<Channel> listChannel = channelCategoryService.getChannels();
		model.addAttribute("listChannel", listChannel);
		return "admin/channelcategory";
	}
	
	@RequestMapping("/saveOrUpdateCatetory")
	@ResponseBody
	public boolean saveOrUpdateCatetory(Model model,String name,Integer chid,Integer cateid){
		if(cateid == null){
			Category category = new Category();
			category.setName(name);
			category.setChannel(new Channel(chid, null));
			channelCategoryService.saveCategory(category);
		}else{
			Category category = new Category();
			category.setId(cateid);
			category.setName(name);
			channelCategoryService.update(category);
		}
		
		return true;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete(Integer id){
		channelCategoryService.removeCategory(id);
		return true;
	}
	@RequestMapping("/toArticle")
	public String toArticle(@RequestParam(defaultValue="1")int page,Model model){
		Article conditions = new Article();
		Page _page = new Page(page, 10);
		List<Article> articleList = articleService.gets(conditions , _page , null);
		model.addAttribute("articleList", articleList);
		return "/admin/article_list";
	}
	
	@RequestMapping("/success")
	@ResponseBody
	public boolean success(Integer id){
		boolean b = articleService.updateStatus(id);
		return b;
	}
	
}
