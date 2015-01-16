package com.youku.share.crowdfunding.manager;

import java.util.List;

import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.SysRole;

public interface SysRoleManager {

	SysRole find(Long roleId);
	
	SysRole save(SysRole sysRole);
    
    Page<SysRole> pageQuery(SysRole sysRole);
    
    boolean delete(Long roleId);
    
    SysRole update(SysRole sysRole);
    
    List<SysRole> getAll();
}
