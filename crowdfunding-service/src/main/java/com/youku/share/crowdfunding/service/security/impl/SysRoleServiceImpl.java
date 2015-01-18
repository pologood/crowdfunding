package com.youku.share.crowdfunding.service.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.youku.share.crowdfunding.manager.SysRoleManager;
import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.SysRole;
import com.youku.share.crowdfunding.service.security.SysRoleService;

@Component
@Service("sysRoleService")
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
	public SysRole update(SysRole sysRole){
		return sysRoleManager.update(sysRole);
	}

}
