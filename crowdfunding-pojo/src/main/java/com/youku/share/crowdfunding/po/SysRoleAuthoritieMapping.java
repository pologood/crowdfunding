package com.youku.share.crowdfunding.po;

public class SysRoleAuthoritieMapping extends BasePO {
    private Long raMappingId;
    private Long roleId;
    private Long authId;

    public Long getRaMappingId() {
        return raMappingId;
    }

    public void setRaMappingId(Long raMappingId) {
        this.raMappingId = raMappingId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

}
