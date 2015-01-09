package com.youku.share.crowdfunding.service.demo.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.youku.share.crowdfunding.manager.UserManager;
import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.User;
import com.youku.share.crowdfunding.service.demo.UserRegistionService;

@Component
public class UserRegistionServiceImpl implements UserRegistionService {

	private static final Logger logger = Logger.getLogger(UserRegistionServiceImpl.class);
	
	@Autowired
	private UserManager userManager;
	
	@Override
	public boolean regist(User user) {
		try{
			userManager.save(user);
			//通知
			//邮件
			return true;
		}catch(Exception e){
			logger.error("========= regist fail =========",e);
		}
		return false;
	}

	@Override
	public Page<User> page(User user) {
		try{
			return userManager.pageQuery(user);
		}catch(Exception e){
			logger.error("========= page fail =========",e);
		}
		return null;
	}
	
	@Override
	public boolean removeUser(Long id) {
		try{
			return userManager.delete(id);
		}catch(Exception e){
			logger.error("========= delete fail =========",e);
		}
		return false;
	}
	
}
