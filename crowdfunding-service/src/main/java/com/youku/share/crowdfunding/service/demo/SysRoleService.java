package com.youku.share.crowdfunding.service.demo;

import java.util.List;

import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.SysRole;

public interface SysRoleService {
	Page<SysRole> page(SysRole sysRole);
	List<SysRole> getAll();
}
