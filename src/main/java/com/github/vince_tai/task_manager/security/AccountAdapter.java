package com.github.vince_tai.task_manager.security;

import com.github.vince_tai.task_manager.domain.entity.Account;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AccountAdapter implements UserDetails {
    private final Account account;
    public AccountAdapter(Account account) {
        this.account = account;
    }

    @Override
    @NonNull
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(account.getAuthority()));
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    @NonNull
    public String getUsername() {
        return account.getEmail();
    }
}
