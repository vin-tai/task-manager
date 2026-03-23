package com.github.vince_tai.task_manager.security;

import com.github.vince_tai.task_manager.domain.entity.Account;
import com.github.vince_tai.task_manager.domain.repository.AccountRepository;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@NullMarked
public class AccountUserDetailsService implements UserDetailsService {
    private AccountRepository repository;
    public AccountUserDetailsService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        return new AccountAdapter(account);
    }

}
