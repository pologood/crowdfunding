package com.youku.share.crowdfunding.po;

import java.util.List;

public class SysUser extends BasePO {
	private Long userId;
	private String userName;
	private String password;
	private String email;

	private List<SysRole> sysRoleList;
	private List<SysUserRoleMapping> sysUserRoleMappingList;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

	public List<SysUserRoleMapping> getSysUserRoleMappingList() {
		return sysUserRoleMappingList;
	}

	public void setSysUserRoleMappingList(
			List<SysUserRoleMapping> sysUserRoleMappingList) {
		this.sysUserRoleMappingList = sysUserRoleMappingList;
	}

}
