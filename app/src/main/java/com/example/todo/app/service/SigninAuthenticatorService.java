package com.example.todo.app.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.exception.CredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todo.app.dao.AccountDao;
import com.example.todo.app.entity.Account;
import com.example.todo.app.helper.DateHelper;

@Service
public class SigninAuthenticatorService implements Authenticator<UsernamePasswordCredentials> {

    private final AccountDao accountDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void validate(UsernamePasswordCredentials credentials, WebContext context) {
        String username = Optional.ofNullable(credentials)
                .map(UsernamePasswordCredentials::getUsername)
                .filter(StringUtils::isNotEmpty)
                .orElseThrow(() -> new CredentialsException("No Username"));
        
        Optional<Account> accountOpt = accountDao.selectByUsername(username);
        
        String rawPassword = credentials.getPassword();
        accountOpt.map(Account::getHashedPassword)
        .filter(encodedPassword -> passwordEncoder.matches(rawPassword, encodedPassword))
        .orElseThrow(() -> new CredentialsException("No Username"));
        
        
        
    }

    @Autowired
    public SigninAuthenticatorService(AccountDao accountDao, PasswordEncoder passwordEncoder) {
        this.accountDao = accountDao;
        this.passwordEncoder = passwordEncoder;
    }
}
