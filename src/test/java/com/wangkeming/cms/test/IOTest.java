package com.wangkeming.cms.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wangkeming.cms.dao.ArticleMapper;
import com.wangkeming.cms.domain.Article;
import com.wangkeming.cms.domain.User;

public class IOTest {
	
	@Test
	public void test01() throws Exception{
		
		//获取添加文章的mapper
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
		ArticleMapper mapper = applicationContext.getBean(ArticleMapper.class);
		
		//获取流
		BufferedReader bufferedReader = null;
		String str = null;
		File file = new File("E:\\大作家");
		Article article = new Article();
		File[] files = file.listFiles();
		for (File file2 : files) {
			String string = "";
			
			String title = file2.getName().substring(0,file2.getName().indexOf(".txt"));
			
			bufferedReader = new BufferedReader(new FileReader(file2));
			while((str = bufferedReader.readLine()) != null){
				string += str;
			}
			article = new Article(null, title, "摘要部分", string, null, null, null, new User(4), 5, true, 1, false, new Date(), new Date());
			
			mapper.save(article);
		}
		
		bufferedReader.close();
	}
}
