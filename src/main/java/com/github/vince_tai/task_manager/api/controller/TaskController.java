package com.github.vince_tai.task_manager.api.controller;

import com.github.vince_tai.task_manager.api.dto.TaskRequest;
import com.github.vince_tai.task_manager.api.dto.TaskResponse;
import com.github.vince_tai.task_manager.domain.entity.Task;
import com.github.vince_tai.task_manager.security.AccountAdapter;
import com.github.vince_tai.task_manager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskService service;

    @PostMapping(path = "/api/tasks")
    public ResponseEntity<TaskResponse> createTask(
            @RequestBody @Valid TaskRequest request,
            @AuthenticationPrincipal AccountAdapter accountAdapter
    ) {
        TaskResponse response = service.create(request, accountAdapter.getUsername());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path = "/api/tasks")
    public ResponseEntity<List<TaskResponse>> getTasks(@RequestParam(required = false) String author) {
        return ResponseEntity.ok().body(service.getTasks(author));
    }
}
