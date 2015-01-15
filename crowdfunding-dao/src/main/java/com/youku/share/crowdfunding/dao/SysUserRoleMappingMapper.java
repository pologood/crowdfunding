package com.youku.share.crowdfunding.dao;

import java.util.List;

import com.youku.share.crowdfunding.po.SysUserRoleMapping;

public interface SysUserRoleMappingMapper {
	int insert(SysUserRoleMapping sysUserRoleMapping);
	int deleteByPrimaryKey(Long urMappingId);
	List<SysUserRoleMapping> selectWhere(SysUserRoleMapping sysUserRoleMapping);
}
