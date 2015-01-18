package com.youku.share.crowdfunding.web.security;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
public class PasswordUtil implements ApplicationContextAware, ServletContextAware {
    
	@SuppressWarnings("unused")
	private static ServletContext servletContext;
    private static ApplicationContext applicationContext;

	/**
	 * Set the {@link ServletContext} that this object runs in.
	 * <p>Invoked after population of normal bean properties but before an init
	 * callback like InitializingBean's {@code afterPropertiesSet} or a
	 * custom init-method. Invoked after ApplicationContextAware's
	 * {@code setApplicationContext}.
	 * @param servletContext ServletContext object to be used by this object
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext
	 */
    @Override
    public void setServletContext(ServletContext servletContext) {
        PasswordUtil.servletContext = servletContext;
    }

	/**
	 * Set the ApplicationContext that this object runs in.
	 * Normally this call will be used to initialize the object.
	 * <p>Invoked after population of normal bean properties but before an init callback such
	 * as {@link org.springframework.beans.factory.InitializingBean#afterPropertiesSet()}
	 * or a custom init-method. Invoked after {@link ResourceLoaderAware#setResourceLoader},
	 * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
	 * {@link MessageSourceAware}, if applicable.
	 * @param applicationContext the ApplicationContext object to be used by this object
	 * @throws ApplicationContextException in case of context initialization errors
	 * @throws BeansException if thrown by application context methods
	 * @see org.springframework.beans.factory.BeanInitializationException
	 */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        PasswordUtil.applicationContext = applicationContext;
    }

    public static String encodePassword(String password){
        return encodePassword(password,null);
    }
    
    public static String encodePassword(String password, String salt){
        Md5PasswordEncoder md5PasswordEncoder = (Md5PasswordEncoder)applicationContext.getBean("passwdEcoder");
        return md5PasswordEncoder.encodePassword(password,salt);
    }
}
