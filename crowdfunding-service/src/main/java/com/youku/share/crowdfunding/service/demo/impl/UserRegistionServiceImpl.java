package com.youku.share.crowdfunding.service.demo.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.youku.share.crowdfunding.manager.UserManager;
import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.User;
import com.youku.share.crowdfunding.service.demo.UserRegistionService;

@Component
public class UserRegistionServiceImpl implements UserRegistionService {

	private static final Logger logger = LogManager.getLogger(UserRegistionServiceImpl.class);
	
	@Autowired
	private UserManager userManager;
	
	@Override
	public boolean regist(User user) {
		logger.info("创建用户" + user.getName());
		userManager.save(user);
		logger.info("向XX系统推送用户信息");		
		//通知
		logger.info("发邮件通知用户注册成功");
		//邮件
		return true;
	}

	@Override
	public Page<User> page(User user) {
		return userManager.pageQuery(user);
	}
	
	@Override
	public boolean removeUser(Long id) {
		return userManager.delete(id);
	}
	
}
