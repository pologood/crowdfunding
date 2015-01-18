package com.youku.share.crowdfunding.manager;

import static junit.framework.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.youku.share.crowdfunding.po.SysUser;

public class SysUserManagerTest {
	
	private static final Logger logger = LogManager.getLogger(SysUserManagerTest.class);

	private ApplicationContext ctx = null;
	private SysUserManager sysUserManager = null;
	
	@Before
	public void init() throws Exception {
		ctx = //new ClassPathXmlApplicationContext("classpath:spring.xml");
		      new ClassPathXmlApplicationContext(new String[]{"classpath:spring-dao.xml"});
		sysUserManager = ctx.getBean(SysUserManager.class);
	}

	@Test
	public void testSysUserManagerSelectWhere() throws Exception {
		SysUser sysUser = new SysUser();
		sysUser.setUserName("pandy");
		List<SysUser> list = sysUserManager.find(sysUser);
		assertNotNull(list);
		for(SysUser oneSysUser : list){
			logger.info(oneSysUser.getUserId());
		}
	}
	
	@After
	public void destroy() throws Exception {
		
	}
}
