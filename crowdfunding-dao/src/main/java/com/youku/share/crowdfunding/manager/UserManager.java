package com.youku.share.crowdfunding.manager;

import com.youku.share.crowdfunding.page.Page;
import com.youku.share.crowdfunding.po.User;

public interface UserManager {

	User get(Long id);
	
	User save(User user);
    
    Page<User> pageQuery(User user);
    
    boolean delete(Long id);
}
