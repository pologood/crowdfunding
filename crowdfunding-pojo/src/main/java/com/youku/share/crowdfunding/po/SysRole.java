package com.youku.share.crowdfunding.po;

import java.util.List;

public class SysRole extends BasePO {
    private Long roleId;
    private String name;

    private List<SysAuthoritie> sysAuthoritieList;
    private List<SysRoleAuthoritieMapping> sysRoleAuthoritieMappingList;
    private List<SysUserRoleMapping> sysUserRoleMappingList;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SysAuthoritie> getSysAuthoritieList() {
        return sysAuthoritieList;
    }

    public void setSysAuthoritieList(List<SysAuthoritie> sysAuthoritieList) {
        this.sysAuthoritieList = sysAuthoritieList;
    }

    public List<SysRoleAuthoritieMapping> getSysRoleAuthoritieMappingList() {
        return sysRoleAuthoritieMappingList;
    }

    public void setSysRoleAuthoritieMappingList(
            List<SysRoleAuthoritieMapping> sysRoleAuthoritieMappingList) {
        this.sysRoleAuthoritieMappingList = sysRoleAuthoritieMappingList;
    }

    public List<SysUserRoleMapping> getSysUserRoleMappingList() {
        return sysUserRoleMappingList;
    }

    public void setSysUserRoleMappingList(
            List<SysUserRoleMapping> sysUserRoleMappingList) {
        this.sysUserRoleMappingList = sysUserRoleMappingList;
    }
}
