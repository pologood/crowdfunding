package com.youku.share.crowdfunding.manager;

import static junit.framework.Assert.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.youku.share.crowdfunding.po.SysRole;

public class SysRoleManagerTest {
	
	private static final Logger logger = LogManager.getLogger(SysRoleManagerTest.class);

	private ApplicationContext ctx = null;
	
	@Autowired
	private SysRoleManager sysRoleManager;
	
	@Before
	public void init() throws Exception {
		ctx = //new ClassPathXmlApplicationContext("classpath:spring.xml");
		      new ClassPathXmlApplicationContext(new String[]{"classpath:spring-dao.xml"});
		sysRoleManager = ctx.getBean(SysRoleManager.class);
	}

	@Test
	public void testSysRoleManager() throws Exception {
		logger.info("SysRoleManagerTest.testSysRoleManager start");
		try{
			List<SysRole> sysRoleList = sysRoleManager.getAll();
			SysRole sysRole = sysRoleManager.find(new Long(2L));
			assertNotNull(sysRole);
			assertNotNull(sysRoleList);
			logger.info("SysRoleManagerTest.testSysRoleManager ok");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@After
	public void destroy() throws Exception {
		
	}
}
