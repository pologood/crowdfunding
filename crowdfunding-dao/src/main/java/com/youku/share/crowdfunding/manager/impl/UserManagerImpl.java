package com.youku.share.crowdfunding.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.User;
import com.youku.share.crowdfunding.dao.UserMapper;
import com.youku.share.crowdfunding.manager.UserManager;

/*
 * Manager层要保持debug级别的日志
 * 
 * 
 */

@Component
public class UserManagerImpl implements UserManager{
    
    @Autowired
    private UserMapper userMapper;
    
    public User get(Long id){
        return userMapper.selectByPrimaryKey(id);
    }
    
    @Transactional
    public User save(User user){
        userMapper.insertSelective(user);
        return user;
    }
    
    @Override
    public Page<User> pageQuery(User user) {
        List<User> userList = userMapper.selectPage(user);
        Page<User> page = new Page<User>();
        page.setRows(userList);
        page.setRecords(userMapper.pageCount(user));
        page.setPageParameter(user);
        page.pageCalculation(new User[]{});
        return page;
    }
    
    @Transactional
    public boolean delete(Long id){
        return userMapper.deleteByPrimaryKey(id) > 0;
    }
    
}
