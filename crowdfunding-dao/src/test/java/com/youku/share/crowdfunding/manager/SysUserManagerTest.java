package com.youku.share.crowdfunding.manager;

import static junit.framework.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.User;

public class SysUserManagerTest {
	
	private static final Logger logger = Logger.getLogger(SysUserManagerTest.class);

	private ApplicationContext ctx = null;
	
	@Before
	public void init() throws Exception {
		ctx = //new ClassPathXmlApplicationContext("classpath:spring.xml");
		      new ClassPathXmlApplicationContext(new String[]{"classpath:spring-dao.xml"});
	}

	@Test
	public void testUserManager() throws Exception {
		try{
			//String[] beanNames = ctx.getBeanDefinitionNames();
			//for(String beanName : beanNames){
			//	logger.info("bean name : " + beanName);
			//}
			//Object obj = ctx.getBean("userManagerImpl");
			//assertNotNull(obj);
			//assertNotNull(userManager);
			//Object impl = ctx.getBean(UserManagerImpl.class);
			//assertNotNull(impl);
			UserManager intf = ctx.getBean(UserManager.class);
			assertNotNull(intf);
			User user = new User();
			user.setName("benson");
			user.setAge(30);
			user.setGender("male");
			user.setBackup("benson sefarious");
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			intf.save(user);
			logger.info("user id = " + user.getId());
		}catch(Throwable e){
			logger.error("testUserManager ------------> ", e);
		}
	}

	@Test
	public void testUserManagerSelectPage() throws Exception {
		try{
			UserManager intf = ctx.getBean(UserManager.class);
			assertNotNull(intf);
			User user = new User();
			user.setName("benson");
			user.setAge(30);
			user.setGender("male");
			user.setBackup("benson sefarious");
			user.setOrder_("id");
			user.setSort_("asc");
			user.setPageNum_(1);
			user.setPageSize_(10);
			Page<User> page = intf.pageQuery(user);
			List<User> list = page.getRows();
			for(User oneUser : list){
				System.out.println(oneUser.getId());
			}
		}catch(Throwable e){
			logger.error("testUserManager ------------> ", e);
		}
	}
	
	@After
	public void destroy() throws Exception {
		
	}
}
