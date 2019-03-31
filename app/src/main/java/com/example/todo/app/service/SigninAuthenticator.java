package com.example.todo.app.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.credentials.password.PasswordEncoder;
import org.pac4j.core.profile.CommonProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.app.dao.AccountDao;
import com.example.todo.app.entity.Account;
import com.example.todo.app.exception.AuthenticateException;
import com.example.todo.app.type.AccountStatusType;

@Service
public class SigninAuthenticator implements Authenticator<UsernamePasswordCredentials> {

    private final AccountDao accountDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void validate(UsernamePasswordCredentials credentials, WebContext context) {
        Optional<UsernamePasswordCredentials> credentialOpt = Optional.ofNullable(credentials);
        String username = credentialOpt
                .map(UsernamePasswordCredentials::getUsername)
                .filter(StringUtils::isNotEmpty)
                .orElseThrow(() -> new AuthenticateException("auth.username.required"));
        String rawPassword = credentialOpt.map(UsernamePasswordCredentials::getPassword)
                .orElse(StringUtils.EMPTY);

        Optional<Account> accountOpt = accountDao.selectByUsername(username);
        accountOpt.map(Account::getHashedPassword)
                .filter(encodedPassword -> passwordEncoder.matches(rawPassword, encodedPassword))
                .orElseThrow(() -> new AuthenticateException("auth.password.unmatch"));
        accountOpt.map(Account::getStatus)
                .filter(AccountStatusType::isLoginable)
                .orElseThrow(() -> new AuthenticateException("auth.not.loginable"));
        
        CommonProfile profile = accountOpt.get().toProfile();
        credentials.setUserProfile(profile);
    }

    @Autowired
    public SigninAuthenticator(AccountDao accountDao, PasswordEncoder passwordEncoder) {
        this.accountDao = accountDao;
        this.passwordEncoder = passwordEncoder;
    }
}
