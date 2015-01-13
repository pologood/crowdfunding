package com.youku.share.crowdfunding.po;

import java.util.List;

public class SysAuthoritie extends BasePO {

	private Integer authId;
	private String description;
	private String url;

	private List<SysRoleAuthoritieMapping> sysRoleAuthoritieMappingList;

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<SysRoleAuthoritieMapping> getSysRoleAuthoritieMappingList() {
		return sysRoleAuthoritieMappingList;
	}

	public void setSysRoleAuthoritieMappingList(
			List<SysRoleAuthoritieMapping> sysRoleAuthoritieMappingList) {
		this.sysRoleAuthoritieMappingList = sysRoleAuthoritieMappingList;
	}

}
