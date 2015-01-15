package com.youku.share.crowdfunding.service.security;

import java.util.List;

import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.SysRole;

public interface SysRoleService {
	Page<SysRole> page(SysRole sysRole);
	List<SysRole> getAll();
	public SysRole update(SysRole sysRole);
}
