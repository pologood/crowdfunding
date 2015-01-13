package com.youku.share.crowdfunding.dao;

import java.util.List;

import com.youku.share.crowdfunding.po.SysUser;

public interface SysAuthoritieMapper {
	SysUser selectByPrimaryKey(Long id);
	int insert(SysUser sysUser);
	int insertSelective(SysUser sysUser);
	int updateByPrimaryKeySelective(SysUser sysUser);
	int deleteByPrimaryKey(Long id);
	List<SysUser> selectWhere(SysUser sysUser);
	List<SysUser> selectPage(SysUser sysUser);
	int pageCount(SysUser sysUser);
}