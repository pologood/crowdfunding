package com.youku.share.crowdfunding.dao;

import java.util.List;

import com.youku.share.crowdfunding.po.User;

public interface UserMapper {
    User selectByPrimaryKey(Long id);
    int insert(User user);
    int insertSelective(User user);
    int updateByPrimaryKeySelective(User user);
    int deleteByPrimaryKey(Long id);
    List<User> selectWhere(User user);
    List<User> selectPage(User user);
    int pageCount(User user);
}
