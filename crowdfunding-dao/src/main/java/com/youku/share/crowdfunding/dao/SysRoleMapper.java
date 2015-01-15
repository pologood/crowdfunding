package com.youku.share.crowdfunding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.youku.share.crowdfunding.po.SysRole;

public interface SysRoleMapper {
	SysRole selectByPrimaryKey(@Param(value="roleId")Long roleId);
	int insert(SysRole sysRole);
	int insertSelective(SysRole sysRole);
	int updateByPrimaryKeySelective(SysRole sysRole);
	int deleteByPrimaryKey(@Param(value="roleId")Long roleId);
	List<SysRole> selectWhere(SysRole sysRole);
	List<SysRole> selectPage(SysRole sysRole);
	int pageCount(SysRole sysRole);
	List<SysRole> selectAll();
}
