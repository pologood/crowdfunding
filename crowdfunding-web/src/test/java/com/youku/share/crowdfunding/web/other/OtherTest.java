package com.youku.share.crowdfunding.web.other;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.youku.share.crowdfunding.po.SysRole;
import com.youku.share.crowdfunding.service.security.SysRoleService;

public class OtherTest{
    private static final Logger logger = LogManager.getLogger(OtherTest.class);
    
    private ApplicationContext ctx = null;
    private SysRoleService sysRoleService;
    
    @Before
    public void init() throws Exception {
        ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-web.xml"});
        sysRoleService = ctx.getBean(SysRoleService.class);
    }
    
    @Test
    public void testGetAll(){
        List<SysRole> sysRoleList = sysRoleService.getAll();
        for(SysRole sysRole : sysRoleList){
            logger.info("sysRole.name = " + sysRole.getName());
        }
    }
    
    @After
    public void destroy() throws Exception {
        
    }
}
