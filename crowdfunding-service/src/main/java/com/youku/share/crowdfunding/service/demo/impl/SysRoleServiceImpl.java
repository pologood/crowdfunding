package com.youku.share.crowdfunding.service.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.youku.share.crowdfunding.manager.SysRoleManager;
import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.SysRole;
import com.youku.share.crowdfunding.service.demo.SysRoleService;

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

}
