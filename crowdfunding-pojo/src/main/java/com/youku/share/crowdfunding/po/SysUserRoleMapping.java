package com.youku.share.crowdfunding.po;

public class SysUserRoleMapping extends BasePO {
	private Integer urMappingId;
	private Integer userId;
	private Integer roleId;

	public Integer getUrMappingId() {
		return urMappingId;
	}

	public void setUrMappingId(Integer urMappingId) {
		this.urMappingId = urMappingId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
