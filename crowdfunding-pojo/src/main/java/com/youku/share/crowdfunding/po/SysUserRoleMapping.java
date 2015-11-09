package com.youku.share.crowdfunding.po;

public class SysUserRoleMapping extends BasePO {
    private Long urMappingId;
    private Long userId;
    private Long roleId;

    public Long getUrMappingId() {
        return urMappingId;
    }

    public void setUrMappingId(Long urMappingId) {
        this.urMappingId = urMappingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
