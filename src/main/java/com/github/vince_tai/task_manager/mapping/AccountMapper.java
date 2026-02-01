package com.github.vince_tai.task_manager.mapping;

import com.github.vince_tai.task_manager.api.dto.AccountRegistrationRequest;
import com.github.vince_tai.task_manager.domain.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountRegistrationRequest request);
}
