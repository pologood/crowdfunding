package com.youku.share.crowdfunding.service.demo;

import static junit.framework.Assert.*;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.User;

public class UserRegistionTest {
	private static final Logger logger = LogManager.getLogger(UserRegistionTest.class);
	
	private ApplicationContext ctx = null;	
	
	private UserRegistionService userRegistionService;
	
	@Before
	public void init() throws Exception {
		ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-service.xml"});
		userRegistionService = ctx.getBean(UserRegistionService.class);
	}

	@Test
	public void testUserRegist() throws Exception {
		logger.info("========= UserRegistionTest.testUserRegist ==========");
		User user = new User();
		user.setName("benson");
		user.setAge(30);
		user.setGender("male");
		user.setBackup("benson sefarious service test regist");
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		userRegistionService.regist(user);
		assertNotNull(user.getId());
	}
	
	@Test
	public void testUserPage() throws Exception {
		logger.info("========= UserRegistionTest.testUserPage ==========");
		User user = new User();
		user.setName("benson");
		user.setAge(30);
		user.setGender("male");
		user.setBackup("benson sefarious service test regist");
		user.setOrder_("id");
		user.setSort_("asc");
		user.setPageNum_(1);
		user.setPageSize_(10);
		Page<User> list = userRegistionService.page(user);
		for(User oneUser : list.getRows()){
			System.out.println(oneUser.getId());
		}
	}
	
	@After
	public void destroy() throws Exception {
		
	}
	
}
