package com.youku.share.crowdfunding.po;

import java.util.List;

public class SysUser extends BasePO {
	private Integer userId;
	private String userName;
	private String password;
	private String email;

	private List<SysRole> sysRoleList;
	private List<SysUserRoleMapping> sysUserRoleMapping;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<SysRole> getSysRoleList() {
		return sysRoleList;
	}

	public void setSysRoleList(List<SysRole> sysRoleList) {
		this.sysRoleList = sysRoleList;
	}

	public List<SysUserRoleMapping> getSysUserRoleMapping() {
		return sysUserRoleMapping;
	}

	public void setSysUserRoleMapping(
			List<SysUserRoleMapping> sysUserRoleMapping) {
		this.sysUserRoleMapping = sysUserRoleMapping;
	}
}
