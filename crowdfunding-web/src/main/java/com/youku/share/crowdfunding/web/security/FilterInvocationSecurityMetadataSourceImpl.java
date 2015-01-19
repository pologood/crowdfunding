package com.youku.share.crowdfunding.web.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.security.web.util.AntPathRequestMatcher;deprecated
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.RequestMatcher;deprecated
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.youku.share.crowdfunding.po.SysAuthoritie;
import com.youku.share.crowdfunding.po.SysRole;
import com.youku.share.crowdfunding.service.security.SysRoleService;

/**
 * Implemented by classes that store and can identify the {@link ConfigAttribute}s that applies to a given secure object
 * invocation.
 *
 * @author Ben Alex
 */
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements
		FilterInvocationSecurityMetadataSource {

	private static final Logger logger = LogManager
			.getLogger(FilterInvocationSecurityMetadataSourceImpl.class);

	/*
	 * key = Authoritie.url value = Role.name
	 */
	private static Map<String, Collection<ConfigAttribute>> arMap;

	//@Autowired(required=true)
	@Resource(name = "sysRoleService")
	private SysRoleService sysRoleService;

	public FilterInvocationSecurityMetadataSourceImpl(SysRoleService sysRoleService) {
		logger.info("init authoritie to role Map constructor arg sysRoleService = " + sysRoleService);
		this.sysRoleService = sysRoleService;
		initRaMap();
	}

	public FilterInvocationSecurityMetadataSourceImpl() {
		logger.info("init authoritie to role Map");
		initRaMap();
	}
	
	/**
	 * If available, returns all of the {@code ConfigAttribute}s defined by the
	 * implementing class.
	 * <p>
	 * This is used by the {@link AbstractSecurityInterceptor} to perform
	 * startup time validation of each {@code ConfigAttribute} configured
	 * against it.
	 *
	 * @return the {@code ConfigAttribute}s or {@code null} if unsupported
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		logger.error("getAllConfigAttributes");

		return null;
	}

	/**
	 * Accesses the {@code ConfigAttribute}s that apply to a given secure
	 * object.
	 *
	 * @param object
	 *            the object being secured
	 *
	 * @return the attributes that apply to the passed in secured object. Should
	 *         return an empty collection if there are no applicable attributes.
	 *
	 * @throws IllegalArgumentException
	 *             if the passed object is not of a type supported by the
	 *             <code>SecurityMetadataSource</code> implementation
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		logger.info("getAttributes : object = " + object);

		initRaMap();
		
		HttpServletRequest request = ((FilterInvocation) object)
				.getHttpRequest();
		
		Collection<ConfigAttribute> roleCollection = new HashSet<ConfigAttribute>();
		
		RequestMatcher matcher = null;
		for (Iterator<String> iter = arMap.keySet().iterator(); iter.hasNext();) {
			String oneAuthoritieUrl = iter.next();
			matcher = new AntPathRequestMatcher(oneAuthoritieUrl);
			if (null != oneAuthoritieUrl && matcher.matches(request)) {
				Collection<ConfigAttribute> oneRoleCollection = arMap.get(oneAuthoritieUrl);
				for(ConfigAttribute oneConfigAttribute : oneRoleCollection){
					if(!roleCollection.contains(oneConfigAttribute)){
						roleCollection.add(oneConfigAttribute);
					}
				}
			}
		}
		
		if(roleCollection.size() > 0){
			return roleCollection;
		}
		
		throw new IllegalArgumentException("undefine request uri[" + request.getRequestURI() + "]");
	}

	/**
	 * Indicates whether the {@code SecurityMetadataSource} implementation is
	 * able to provide {@code ConfigAttribute}s for the indicated secure object
	 * type.
	 *
	 * @param clazz
	 *            the class that is being queried
	 *
	 * @return true if the implementation can process the indicated class
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		logger.error("getAllConfigAttributes ： clazz = " + clazz.getName());

		return true;
	}

	/*
	 * 修改角色或者资源后是否需要刷新此缓存数据？
	 * 如果一个请求的资源找不到对应的角色，那么此资源默认不受访问限制
	 */
	private void initRaMap() {
		if (arMap == null) {
			synchronized (this) {
				if (arMap == null) {
					if(sysRoleService == null)return;
					arMap = new HashMap<String, Collection<ConfigAttribute>>();
					List<SysRole> sysRoleList = sysRoleService.getAll();
					if (sysRoleList != null && sysRoleList.size() > 0) {
						for (SysRole sysRole : sysRoleList) {
							List<SysAuthoritie> authoritieList = sysRole
									.getSysAuthoritieList();
							if (authoritieList != null
									&& authoritieList.size() > 0) {
								for (SysAuthoritie sysAuthoritie : authoritieList) {
									addConfigAttribute(arMap,
											sysAuthoritie.getUrl(),
											sysRole.getName());
								}
							}
						}
					}
				}
			}
		}
	}

	private void addConfigAttribute(
			Map<String, Collection<ConfigAttribute>> map, String key,
			String valueString) {
		Collection<ConfigAttribute> value = map.get(key);
		if (value == null) {
			value = new HashSet<ConfigAttribute>();
		}

		ConfigAttribute configAttribute = new SecurityConfig(valueString);
		if (!value.contains(configAttribute)) {
			value.add(configAttribute);
		}

		map.put(key, value);
	}

	public void setSysRoleService(
			SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

}
