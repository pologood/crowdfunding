package com.youku.share.crowdfunding.web.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.youku.share.crowdfunding.manager.SysUserManager;
import com.youku.share.crowdfunding.po.SysRole;
import com.youku.share.crowdfunding.po.SysUser;

/**
 * Core interface which loads user-specific data.
 * <p>
 * It is used throughout the framework as a user DAO and is the strategy used by
 * the
 * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider
 * DaoAuthenticationProvider}.
 *
 * <p>
 * The interface requires only one read-only method, which simplifies support
 * for new data-access strategies.
 *
 * @see org.springframework.security.authentication.dao.DaoAuthenticationProvider
 * @see UserDetails
 *
 * @author Ben Alex
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LogManager
            .getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private SysUserManager sysUserManager;

    /**
     * Locates the user based on the username. In the actual implementation, the
     * search may possibly be case sensitive, or case insensitive depending on
     * how the implementation instance is configured. In this case, the
     * <code>UserDetails</code> object that comes back may have a username that
     * is of a different case than what was actually requested..
     *
     * @param username
     *            the username identifying the user whose data is required.
     *
     * @return a fully populated user record (never <code>null</code>)
     *
     * @throws UsernameNotFoundException
     *             if the user could not be found or the user has no
     *             GrantedAuthority
     */
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        logger.info("loadUserByUsername username = " + username);
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        List<SysUser> sysUserList = sysUserManager.find(sysUser);
        if (sysUserList != null && sysUserList.size() == 1) {
            SysUser storedSysUser = sysUserList.get(0);
            return new User(storedSysUser.getUserName(), storedSysUser.getPassword(), true,
                    true, true, true, getGrantedSysRoles(storedSysUser));
        }
        throw new UsernameNotFoundException("can not find username = "
                + username);
    }

    private Set<GrantedAuthority> getGrantedSysRoles(SysUser sysUser) {
        List<SysRole> sysRoleList = sysUser.getSysRoleList();
        Set<GrantedAuthority> grantedSysRoles = new HashSet<GrantedAuthority>();
        for (SysRole sysRole : sysRoleList) {
            grantedSysRoles.add(new SimpleGrantedAuthority(sysRole.getName()));
        }
        return grantedSysRoles;
    }
}
