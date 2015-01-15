package com.youku.share.crowdfunding.service.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.youku.share.crowdfunding.exception.BusinessException;
import com.youku.share.crowdfunding.manager.SysRoleManager;
import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.SysRole;
import com.youku.share.crowdfunding.service.security.SysRoleService;

@Component
public class SysRoleServiceImpl implements SysRoleService{

	@Autowired
	private SysRoleManager sysRoleManager;
	
	@Override
	public Page<SysRole> page(SysRole sysRole) {
		return sysRoleManager.pageQuery(sysRole);
	}

	@Override
	public List<SysRole> getAll() {
		return sysRoleManager.getAll();
	}
	
	@Override
	public SysRole update(SysRole sysRole) throws BusinessException{
		if(sysRole.getSysAuthoritieList().size() > 3){
			
			throw new BusinessException("errCode","单个资源角色不能超过三个");
		}
		
		
		return sysRoleManager.update(sysRole);
	}

}