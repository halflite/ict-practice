package com.example.todo.app.auth;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.todo.app.entity.Account;
import com.example.todo.app.type.AccountStatusType;

public class AccountDetails implements UserDetails {

    private static final long serialVersionUID = 1596804545773727507L;
    
    private final String username;
    private final String hashedPassword;
    private final AccountStatusType status;
    
    public AccountDetails(Account account) {
        this.username = account.getUsername();
        this.hashedPassword = account.getHashedPassword();
        this.status = account.getStatus();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO 後で
        return Collections.emptySet();
    }

    @Override
    public String getPassword() {
        return this.hashedPassword;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status.isLoginable();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
