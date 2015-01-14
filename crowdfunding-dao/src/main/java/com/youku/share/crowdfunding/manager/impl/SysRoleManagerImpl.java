package com.youku.share.crowdfunding.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.SysRole;
import com.youku.share.crowdfunding.po.SysRoleAuthoritieMapping;
import com.youku.share.crowdfunding.po.SysUserRoleMapping;
import com.youku.share.crowdfunding.dao.SysRoleAuthoritieMappingMapper;
import com.youku.share.crowdfunding.dao.SysRoleMapper;
import com.youku.share.crowdfunding.dao.SysUserRoleMappingMapper;
import com.youku.share.crowdfunding.manager.SysRoleManager;

@Component
public class SysRoleManagerImpl implements SysRoleManager{
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRoleAuthoritieMappingMapper sysRoleAuthoritieMappingMapper;
	@Autowired
	private SysUserRoleMappingMapper sysUserRoleMappingMapper;
	
	public SysRole save(SysRole sysRole){
		sysRoleMapper.insertSelective(sysRole);		
		return sysRole;
	}
	
	@Override
	public Page<SysRole> pageQuery(SysRole sysRole) {
		List<SysRole> userList = sysRoleMapper.selectPage(sysRole);
		Page<SysRole> page = new Page<SysRole>();
		page.setRows(userList);
		page.setRecords(sysRoleMapper.pageCount(sysRole));
		page.setPageParameter(sysRole);
		page.pageCalculation(new SysRole[]{});
		return page;
	}
	
	@Transactional
	public boolean delete(Long id){
		SysRole oldSysRole = sysRoleMapper.selectByPrimaryKey(id);
		List<SysUserRoleMapping> oldSysUserRoleMappingList = oldSysRole.getSysUserRoleMappingList();
		if(oldSysUserRoleMappingList != null && oldSysUserRoleMappingList.size() > 0){
			for(SysUserRoleMapping sysRoleRoleMapping : oldSysUserRoleMappingList){
				sysUserRoleMappingMapper.deleteByPrimaryKey(sysRoleRoleMapping.getUrMappingId());
			}
		}
		List<SysRoleAuthoritieMapping> oldSysRoleAuthoritieMappingList = oldSysRole.getSysRoleAuthoritieMappingList();
		if(oldSysRoleAuthoritieMappingList != null && oldSysRoleAuthoritieMappingList.size() > 0){
			for(SysRoleAuthoritieMapping sysRoleAuthoritieMapping : oldSysRoleAuthoritieMappingList){
				sysRoleAuthoritieMappingMapper.deleteByPrimaryKey(sysRoleAuthoritieMapping.getRaMappingId());
			}
		}
		return sysRoleMapper.deleteByPrimaryKey(id) > 0;
	}

	@Transactional
	public SysRole update(SysRole sysRole) {
		SysRole oldSysRole = sysRoleMapper.selectByPrimaryKey(sysRole.getRoleId());
		List<SysRoleAuthoritieMapping> oldSysRoleAuthoritieMappingList = oldSysRole.getSysRoleAuthoritieMappingList();
		if(oldSysRoleAuthoritieMappingList != null && oldSysRoleAuthoritieMappingList.size() > 0){
			for(SysRoleAuthoritieMapping sysRoleAuthoritieMapping : oldSysRoleAuthoritieMappingList){
				sysRoleAuthoritieMappingMapper.deleteByPrimaryKey(sysRoleAuthoritieMapping.getRaMappingId());
			}
		}
		
		sysRoleMapper.updateByPrimaryKeySelective(sysRole);
		
		List<SysRoleAuthoritieMapping> sysRoleAuthoritieMappingList = oldSysRole.getSysRoleAuthoritieMappingList();
		if(sysRoleAuthoritieMappingList != null && sysRoleAuthoritieMappingList.size() > 0){
			for(SysRoleAuthoritieMapping sysRoleAuthoritieMapping : sysRoleAuthoritieMappingList){
				sysRoleAuthoritieMapping.setRoleId(sysRole.getRoleId());
				sysRoleAuthoritieMappingMapper.insert(sysRoleAuthoritieMapping);
			}
		}
		
		return sysRole;
	}

	@Override
	public SysRole find(Long id) {
		return sysRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysRole> getAll() {
		return sysRoleMapper.selectAll();
	}
	
}
