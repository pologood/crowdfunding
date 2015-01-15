package com.youku.share.crowdfunding.web.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.youku.share.crowdfunding.po.SysAuthoritie;
import com.youku.share.crowdfunding.po.SysRole;
import com.youku.share.crowdfunding.service.security.SysRoleService;

public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

	private static final Logger logger = Logger.getLogger(FilterInvocationSecurityMetadataSourceImpl.class);
	
	/*
	 * key = Authoritie.url
	 * value = Role.name
	 */
	private Map<String, Collection<ConfigAttribute>> arMap; 

	@Autowired
	private SysRoleService sysRoleService;
	
	public FilterInvocationSecurityMetadataSourceImpl(){
		logger.info("init authoritie to role Map");
		initRaMap();
	}
	
    /**
     * If available, returns all of the {@code ConfigAttribute}s defined by the implementing class.
     * <p>
     * This is used by the {@link AbstractSecurityInterceptor} to perform startup time validation of each
     * {@code ConfigAttribute} configured against it.
     *
     * @return the {@code ConfigAttribute}s or {@code null} if unsupported
     */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Accesses the {@code ConfigAttribute}s that apply to a given secure object.
     *
     * @param object the object being secured
     *
     * @return the attributes that apply to the passed in secured object. Should return an empty collection if there
     *         are no applicable attributes.
     *
     * @throws IllegalArgumentException if the passed object is not of a type supported by the
     *         <code>SecurityMetadataSource</code> implementation
     */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object arg0)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Indicates whether the {@code SecurityMetadataSource} implementation is able to provide
     * {@code ConfigAttribute}s for the indicated secure object type.
     *
     * @param clazz the class that is being queried
     *
     * @return true if the implementation can process the indicated class
     */
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}
	
	private void initRaMap(){
		if(arMap == null){
			synchronized(this){
				Map<String, Collection<ConfigAttribute>> temp = new HashMap<String, Collection<ConfigAttribute>>();
				List<SysRole> sysRoleList = sysRoleService.getAll();
				if(sysRoleList != null && sysRoleList.size() > 0){
					for(SysRole sysRole : sysRoleList){
						List<SysAuthoritie> authoritieList = sysRole.getSysAuthoritieList();
						if(authoritieList != null && authoritieList.size() > 0){
							for(SysAuthoritie sysAuthoritie : authoritieList){
								addConfigAttribute(temp, sysAuthoritie.getUrl(), sysRole.getName());
							}
						}
					}
				}
			}
		}
	}

	private void addConfigAttribute(Map<String, Collection<ConfigAttribute>> map, String key, String valueString){
		Collection<ConfigAttribute> value = map.get(key);
		if(value == null){
			value = new HashSet<ConfigAttribute>();
		}
		
		ConfigAttribute configAttribute = new SecurityConfig(valueString);
		if(!value.contains(configAttribute)){
			value.add(configAttribute);
		}
		
		map.put(key, value);
	}
}
