package com.youku.share.crowdfunding.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youku.share.crowdfunding.po.SysRole;
import com.youku.share.crowdfunding.po.SysRoleAuthoritieMapping;
import com.youku.share.crowdfunding.service.security.SysRoleService;

@Controller
@RequestMapping("/sysrole")
public class SysRoleController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(SysRoleController.class);
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@InitBinder("sysrole")    
	public void initBinder1(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("sysrole.");    
	}
	
	@RequestMapping(value = "/update")
	public @ResponseBody String update(SysRole sysRole, HttpServletRequest request){
		logger.info("修改角色");
		boolean ret = true;
		
		sysRole = new SysRole();
		sysRole.setRoleId(2L);
		
		List<SysRoleAuthoritieMapping> list = new ArrayList<SysRoleAuthoritieMapping>();
		SysRoleAuthoritieMapping m = new SysRoleAuthoritieMapping();
		m.setAuthId(1L);
		list.add(m);
		m = new SysRoleAuthoritieMapping();
		m.setAuthId(2L);
		list.add(m);
		m = new SysRoleAuthoritieMapping();
		m.setAuthId(3L);
		list.add(m);
		m = new SysRoleAuthoritieMapping();
		m.setAuthId(4L);
		list.add(m);
		
		sysRole.setSysRoleAuthoritieMappingList(list);
		sysRoleService.update(sysRole);
		return ("{\"result\":" + ret +"}");
	}

}
