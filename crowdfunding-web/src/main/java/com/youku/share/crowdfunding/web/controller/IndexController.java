package com.youku.share.crowdfunding.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.youku.share.crowdfunding.po.User;
import com.youku.share.crowdfunding.service.demo.UserRegistionService;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
    
	private static final Logger logger = Logger.getLogger(IndexController.class);
	
	@Autowired
	private UserRegistionService userRegistionService;
	
	@RequestMapping(value = "welcome")
	public ModelAndView welcome(String word,HttpServletRequest request){
		logger.info("word==========="+word);
		Map<String,Object> rootMap = new HashMap<String, Object>();
		User user = new User();
		user.setName(word);
		user.setAge(30);
		user.setGender("male");
		user.setBackup("benson sefarious");
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		//rootMap.put("word", word + " regist success ? " + userRegistionService.regist(user));
		return new ModelAndView("index",rootMap);
	}
	
}
