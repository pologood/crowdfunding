package com.youku.share.crowdfunding.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.youku.share.crowdfunding.exception.AuthorityException;
import com.youku.share.crowdfunding.exception.BusinessException;
import com.youku.share.crowdfunding.exception.PlatformException;
import com.youku.share.crowdfunding.service.demo.UserRegistionService;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
    
	private static final Logger logger = LogManager.getLogger(IndexController.class);
	
	@Autowired
	private UserRegistionService userRegistionService;
	
	@RequestMapping(value = "welcome")
	public ModelAndView welcome(HttpServletRequest request){
		logger.info("=====welcome======");
		Map<String,Object> rootMap = new HashMap<String, Object>();
		return new ModelAndView("welcome",rootMap);
	}
	
	@RequestMapping(value = "login")
	public ModelAndView login(HttpServletRequest request){
		logger.info("=====login======");
		Map<String,Object> rootMap = new HashMap<String, Object>();
		return new ModelAndView("login",rootMap);
	}
	
	@RequestMapping(value = "message")
	public ModelAndView message(String message, HttpServletRequest request){
		logger.info("=====message======");
		Map<String,Object> rootMap = new HashMap<String, Object>();
		rootMap.put("message", message);
		return new ModelAndView("index/message",rootMap);
	}
	
	@RequestMapping(value = "test")
	public ModelAndView test(String word, HttpServletRequest request) throws Exception{
		logger.info("word==========="+word);
		Map<String,Object> rootMap = new HashMap<String, Object>();
		if("AuthorityException".equals(word)){
			throw new AuthorityException();
		}
		if("BusinessException".equals(word)){
			throw new BusinessException();
		}
		if("PlatformException".equals(word)){
			throw new PlatformException();
		}
		return new ModelAndView("index",rootMap);
	}
	
}
