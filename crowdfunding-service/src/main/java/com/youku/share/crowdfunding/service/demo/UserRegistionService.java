package com.youku.share.crowdfunding.service.demo;

import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.User;

public interface UserRegistionService {
    boolean regist(User user);
    Page<User> page(User user);
    boolean removeUser(Long id);
}
