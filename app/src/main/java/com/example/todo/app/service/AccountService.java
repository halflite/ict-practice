package com.example.todo.app.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.todo.app.auth.AccountDetails;
import com.example.todo.app.dao.AccountDao;

@Service
public class AccountService implements UserDetailsService {

    private final AccountDao accountDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String nullSafeUsername = Optional.ofNullable(username)
                .filter(StringUtils::isNotEmpty)
                .orElseThrow(() -> new UsernameNotFoundException("denied empty username"));
        AccountDetails details = accountDao.selectByUsername(nullSafeUsername)
                .map(AccountDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("account not found"));
        return details;
    }

    @Autowired
    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
