package com.example.todo.app.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todo.app.dao.AccountDao;
import com.example.todo.app.entity.Account;
import com.example.todo.app.helper.DateHelper;

@Service
public class SignupService {

    private final AccountDao accountDao;
    private final DateHelper dateHelper;
    private final PasswordEncoder passwordEncoder;
    
    public boolean isDuplicatedUsername(String username) {
        Optional<Account> accountOpt= accountDao.selectByUsername(username);
        return accountOpt.isPresent();
    }
    
    public Account register(String username, String password, String name) {
        LocalDateTime now = this.dateHelper.now();
        String hashedPassword = this.passwordEncoder.encode(password);
        Account account = new Account(username, hashedPassword, name, now);
        accountDao.insert(account);
        
        return account;
    }
    
    @Autowired
    public SignupService(AccountDao accountDao, DateHelper dateHelper, PasswordEncoder passwordEncoder) {
        this.accountDao = accountDao;
        this.dateHelper = dateHelper;
        this.passwordEncoder = passwordEncoder;
    }
    
}
