package com.youku.share.crowdfunding.dao;

import com.youku.share.crowdfunding.po.SysUserRoleMapping;

public interface SysUserRoleMappingMapper {
	int insert(SysUserRoleMapping sysUserRoleMapping);
	int deleteByPrimaryKey(Long id);
}
