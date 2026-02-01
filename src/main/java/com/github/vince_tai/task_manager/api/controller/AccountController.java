package com.github.vince_tai.task_manager.api.controller;

import com.github.vince_tai.task_manager.api.dto.AccountRegistrationRequest;
import com.github.vince_tai.task_manager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    AccountService service;

    @PostMapping(path = "/api/accounts")
    public void register(@RequestBody AccountRegistrationRequest request) {
        service.register(request);
    }
}
