package com.abc.crm.service;

import com.abc.crm.dao.UserDao;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    public UserService (UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userDao.getByUsername(username)
                .map(e -> User.withUsername(e.getUsername())
                .password("{noop}" + e.getPassword())
                .roles(e.getRole())
                .build()).orElse(null);
        return userDetails;
    }
}
