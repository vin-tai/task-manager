package com.github.vince_tai.task_manager.mapping;

import com.github.vince_tai.task_manager.api.dto.AccountRegistrationRequest;
import com.github.vince_tai.task_manager.domain.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "authority", constant = "ROLE_USER")
    Account toEntity(AccountRegistrationRequest request);
}
