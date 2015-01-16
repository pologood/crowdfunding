package com.youku.share.crowdfunding.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.User;
import com.youku.share.crowdfunding.service.demo.UserRegistionService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserRegistionService userRegistionService;
	
	@InitBinder("user")    
	public void initBinder1(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("user.");    
	}
	
	@RequestMapping(value = "/list")
	public @ResponseBody Page<User> list(User user, HttpServletRequest request){
		logger.info("========================");
		Page<User> page = userRegistionService.page(user);
		return page;
	}
	
	@RequestMapping(value = "/save")
	public @ResponseBody String save(@RequestBody String body, /*User user, String name, int age, String gender, String backup,*/HttpServletRequest request){
		logger.info("body = " + body);
		
		//		User user = new User();
//		user.setName(name);
//		user.setAge(age);
//		user.setGender(gender);
//		user.setBackup(backup);
//		user.setCreateTime(new Date());
//		user.setUpdateTime(new Date());
		boolean ret = false;
//		ret = userRegistionService.regist(user);
		return ("{\"result\":" + ret +"}");
	}
	
	@RequestMapping(value = "delete")
	public @ResponseBody String delete(Long id,HttpServletRequest request){

		boolean ret = false;
		ret = userRegistionService.removeUser(id);
		return ("{\"result\":" + ret +"}");
	}
	
}
