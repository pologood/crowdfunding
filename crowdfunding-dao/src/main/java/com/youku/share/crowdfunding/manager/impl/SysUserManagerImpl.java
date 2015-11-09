package com.youku.share.crowdfunding.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.SysUser;
import com.youku.share.crowdfunding.po.SysUserRoleMapping;
import com.youku.share.crowdfunding.dao.SysUserMapper;
import com.youku.share.crowdfunding.dao.SysUserRoleMappingMapper;
import com.youku.share.crowdfunding.manager.SysUserManager;

@Component
public class SysUserManagerImpl implements SysUserManager{
    
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMappingMapper sysUserRoleMappingMapper;
    
    public SysUser save(SysUser sysUser){
        sysUserMapper.insertSelective(sysUser);        
        return sysUser;
    }
    
    @Override
    public Page<SysUser> pageQuery(SysUser sysUser) {
        List<SysUser> userList = sysUserMapper.selectPage(sysUser);
        Page<SysUser> page = new Page<SysUser>();
        page.setRows(userList);
        page.setRecords(sysUserMapper.pageCount(sysUser));
        page.setPageParameter(sysUser);
        page.pageCalculation(new SysUser[]{});
        return page;
    }
    
    @Transactional
    public boolean delete(Long id){
        SysUser oldSysUser = sysUserMapper.selectByPrimaryKey(id);
        List<SysUserRoleMapping> oldSysUserRoleMappingList = oldSysUser.getSysUserRoleMappingList();
        if(oldSysUserRoleMappingList != null && oldSysUserRoleMappingList.size() > 0){
            for(SysUserRoleMapping sysUserRoleMapping : oldSysUserRoleMappingList){
                sysUserRoleMappingMapper.deleteByPrimaryKey(sysUserRoleMapping.getUrMappingId());
            }
        }
        return sysUserMapper.deleteByPrimaryKey(id) > 0;
    }

    @Transactional
    public SysUser update(SysUser sysUser) {
        SysUser oldSysUser = sysUserMapper.selectByPrimaryKey(sysUser.getUserId());
        List<SysUserRoleMapping> oldSysUserRoleMappingList = oldSysUser.getSysUserRoleMappingList();
        if(oldSysUserRoleMappingList != null && oldSysUserRoleMappingList.size() > 0){
            for(SysUserRoleMapping sysUserRoleMapping : oldSysUserRoleMappingList){
                sysUserRoleMappingMapper.deleteByPrimaryKey(sysUserRoleMapping.getUrMappingId());
            }
        }
        
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        List<SysUserRoleMapping> sysUserRoleMappingList = sysUser.getSysUserRoleMappingList();
        if(sysUserRoleMappingList != null && sysUserRoleMappingList.size() > 0){
            for(SysUserRoleMapping sysUserRoleMapping : sysUserRoleMappingList){
                sysUserRoleMapping.setUserId(sysUser.getUserId());
                sysUserRoleMappingMapper.insert(sysUserRoleMapping);
            }
        }
        
        return sysUser;
    }

    @Override
    public SysUser find(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }
    
    public List<SysUser> find(SysUser sysUser){
        return sysUserMapper.selectWhere(sysUser);
    }
}
