package com.example.todo.app.service;

import java.util.Optional;

import org.pac4j.core.credentials.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.example.todo.app.dao.AccountDao;
import com.example.todo.app.entity.Account;
import com.example.todo.app.exception.AuthenticateException;
import com.example.todo.app.type.AccountStatusType;

@Service
public class SigninService {

    private final AccountDao accountDao;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    public Account authenticate(String username, String rawPassword) throws AuthenticateException {
        Optional<Account> accountOpt = accountDao.selectByUsername(username);
        accountOpt.map(Account::getHashedPassword)
                .filter(encodedPassword -> passwordEncoder.matches(rawPassword, encodedPassword))
                .orElseThrow(() -> new AuthenticateException("auth.password.unmatch"));
        accountOpt.map(Account::getStatus)
                .filter(AccountStatusType::isLoginable)
                .orElseThrow(() -> new AuthenticateException("auth.not.loginable"));

        return accountOpt.get();
    }
    
    @Autowired
    public SigninService(AccountDao accountDao, PasswordEncoder passwordEncoder, MessageSource messageSource) {
        this.accountDao = accountDao;
        this.passwordEncoder = passwordEncoder;
        this.messageSource = messageSource;
    }
}
