package com.youku.share.crowdfunding.util;

import static junit.framework.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UtilsTest{

	private static final Logger logger = LogManager.getLogger(UtilsTest.class);
	
	//private ApplicationContext ctx = null;	

	@Before
	public void init() throws Exception {
		//ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-util.xml"});
	}

	@Test
	public void testUserRegist() throws Exception {
		logger.info("========= UtilsTest.testUserRegist ==========");
		String serial = Utils.generateSerialNumber();
		logger.info("serial = " + serial);
		serial = Utils.generateSerialNumber();
		logger.info("serial = " + serial);
		serial = Utils.generateSerialNumber();
		logger.info("serial = " + serial);
		serial = Utils.generateSerialNumber();
		logger.info("serial = " + serial);
		serial = Utils.generateSerialNumber();
		logger.info("serial = " + serial);
		assertNotNull(serial);
	}
	
	@After
	public void destroy() throws Exception {
		
	}
	
}