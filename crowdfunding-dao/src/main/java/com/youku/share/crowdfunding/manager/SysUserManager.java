package com.youku.share.crowdfunding.manager;

import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.SysUser;

public interface SysUserManager {

	SysUser find(Long id);
	
	SysUser save(SysUser sysUser);
    
    Page<SysUser> pageQuery(SysUser sysUser);
    
    boolean delete(Long id);
    
    SysUser update(SysUser sysUser);
}
