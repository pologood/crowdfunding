package com.youku.share.crowdfunding.dao;

import java.util.List;

import com.youku.share.crowdfunding.po.SysRoleAuthoritieMapping;

public interface SysRoleAuthoritieMappingMapper {
	int insert(SysRoleAuthoritieMapping sysRoleAuthoritieMapping);
	int deleteByPrimaryKey(Long id);
	List<SysRoleAuthoritieMapping> selectWhere(SysRoleAuthoritieMapping sysRoleAuthoritieMapping);
}
