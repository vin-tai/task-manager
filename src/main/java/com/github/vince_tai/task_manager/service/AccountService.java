package com.github.vince_tai.task_manager.service;

import com.github.vince_tai.task_manager.api.dto.RegisterAccountRequest;
import com.github.vince_tai.task_manager.domain.entity.Account;
import com.github.vince_tai.task_manager.domain.repository.AccountRepository;
import com.github.vince_tai.task_manager.mapping.AccountMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountService {
    private final AccountRepository repository;
    private final AccountMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository repository, AccountMapper mapper,  PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(RegisterAccountRequest request) {
        String email = request.email().toLowerCase();
        if (repository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        Account account = mapper.toEntity(request);
        account.setEmail(email);
        account.setPassword(passwordEncoder.encode(request.password()));
        repository.save(account);
    }
}
