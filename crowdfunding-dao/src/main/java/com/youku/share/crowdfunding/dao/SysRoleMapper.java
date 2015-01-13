package com.youku.share.crowdfunding.dao;

import java.util.List;

import com.youku.share.crowdfunding.po.SysRole;

public interface SysRoleMapper {
	SysRole selectByPrimaryKey(Long id);
	int insert(SysRole sysRole);
	int insertSelective(SysRole sysRole);
	int updateByPrimaryKeySelective(SysRole sysRole);
	int deleteByPrimaryKey(Long id);
	List<SysRole> selectWhere(SysRole sysRole);
	List<SysRole> selectPage(SysRole sysRole);
	int pageCount(SysRole sysRole);
}
