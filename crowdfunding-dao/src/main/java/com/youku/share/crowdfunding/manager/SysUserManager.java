package com.youku.share.crowdfunding.manager;

import java.util.List;

import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.SysUser;

public interface SysUserManager {

	SysUser find(Long id);
	
	List<SysUser> find(SysUser sysUser);
	
	SysUser save(SysUser sysUser);
    
    Page<SysUser> pageQuery(SysUser sysUser);
    
    boolean delete(Long id);
    
    SysUser update(SysUser sysUser);
}
