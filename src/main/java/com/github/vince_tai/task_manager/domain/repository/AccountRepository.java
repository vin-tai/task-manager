package com.github.vince_tai.task_manager.domain.repository;

import com.github.vince_tai.task_manager.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByEmail(String email);
}
