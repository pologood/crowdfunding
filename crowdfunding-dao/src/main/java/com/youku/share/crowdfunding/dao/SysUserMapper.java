package com.youku.share.crowdfunding.dao;

import java.util.List;

import com.youku.share.crowdfunding.po.SysUser;

public interface SysUserMapper {
	SysUser selectByPrimaryKey(Long userId);
	int insert(SysUser sysUser);
	int insertSelective(SysUser sysUser);
	int updateByPrimaryKeySelective(SysUser sysUser);
	int deleteByPrimaryKey(Long userId);
	List<SysUser> selectWhere(SysUser sysUser);
	List<SysUser> selectPage(SysUser sysUser);
	int pageCount(SysUser sysUser);
}
