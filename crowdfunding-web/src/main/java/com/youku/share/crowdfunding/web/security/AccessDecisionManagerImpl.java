package com.youku.share.crowdfunding.web.security;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.youku.share.crowdfunding.constants.Constants;

/**
 * Makes a final access control (authorization) decision.
 *
 * @author Ben Alex
 */
@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {

	private static final Logger logger = LogManager.getLogger(AccessDecisionManagerImpl.class);
	
	/**
	 * Resolves an access control decision for the passed parameters.
	 * decide 方法接受三个参数， 第一个使用户拥有的角色， 第二个参数就是在 过滤器中新建的 FilterInvocation 对象，
	 * 第三个参数就是该访问路径对应的角色集合。 然后可以根据 用户角色和菜单角色对比，判断该用户是否具有该路径的访问资格。 如果没有，抛出异常。
	 *
	 * @param authentication //用户的角色信息
	 *            the caller invoking the method (not null)
	 * @param object //当前被访问的菜单
	 *            the secured object being called
	 * @param configAttributes //菜单对应的角色
	 *            the configuration attributes associated with the secured
	 *            object being invoked
	 *
	 * @throws AccessDeniedException
	 *             if access is denied as the authentication does not hold a
	 *             required authority or ACL privilege
	 * @throws InsufficientAuthenticationException
	 *             if access is denied as the authentication does not provide a
	 *             sufficient level of trust
	 */
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		logger.info("decide for user : " + authentication.getName());
		for(GrantedAuthority ga : authentication.getAuthorities()){
			logger.debug("one GrantedAuthority : " + ga.getAuthority());
			if(Constants.SUPER_ROLE.equals(ga.getAuthority())){
				return;
			}
		}
		logger.info("the secured object being called : " + object);
		logger.info("the configuration attributes associated with the secured object being invoked : fellow echo");
		for(ConfigAttribute ca : configAttributes){
			logger.debug("one ConfigAttribute : " + ca.getAttribute());
		}
		
		for(ConfigAttribute ca : configAttributes){
			for(GrantedAuthority ga : authentication.getAuthorities()){
				if(ca.getAttribute().equals(ga.getAuthority())){
					return;
				}
			}
		}
		
		throw new AccessDeniedException("user " + authentication.getName() + " not enough authority !");
	}

	/**
	 * Indicates whether this <code>AccessDecisionManager</code> is able to
	 * process authorization requests presented with the passed
	 * <code>ConfigAttribute</code>.
	 * <p>
	 * This allows the <code>AbstractSecurityInterceptor</code> to check every
	 * configuration attribute can be consumed by the configured
	 * <code>AccessDecisionManager</code> and/or <code>RunAsManager</code>
	 * and/or <code>AfterInvocationManager</code>.
	 * </p>
	 *
	 * @param attribute
	 *            a configuration attribute that has been configured against the
	 *            <code>AbstractSecurityInterceptor</code>
	 *
	 * @return true if this <code>AccessDecisionManager</code> can support the
	 *         passed configuration attribute
	 */
	@Override
	public boolean supports(ConfigAttribute attribute) {
		logger.error("supports attribute = " + attribute.getAttribute());
		return true;
	}

	/**
	 * Indicates whether the <code>AccessDecisionManager</code> implementation
	 * is able to provide access control decisions for the indicated secured
	 * object type.
	 *
	 * @param clazz
	 *            the class that is being queried
	 *
	 * @return <code>true</code> if the implementation can process the indicated
	 *         class
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		logger.error("supports clazz = " + clazz.getName());
		return true;
	}

}
