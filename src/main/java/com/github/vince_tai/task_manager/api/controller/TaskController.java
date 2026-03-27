package com.github.vince_tai.task_manager.api.controller;

import com.github.vince_tai.task_manager.api.dto.TaskRequest;
import com.github.vince_tai.task_manager.domain.entity.Task;
import com.github.vince_tai.task_manager.security.AccountAdapter;
import com.github.vince_tai.task_manager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @Autowired
    TaskService service;

    @PostMapping(path = "/api/tasks")
    public ResponseEntity<Task> createTask(
            @RequestBody @Valid TaskRequest request,
            @AuthenticationPrincipal AccountAdapter accountAdapter
    ) {
        Task task = service.create(request, accountAdapter.getUsername());
        return ResponseEntity.ok().body(task);
    }

    @GetMapping(path = "/api/tasks")
    public ResponseEntity<Void> getAllTasks() {
        return ResponseEntity.ok().build();
    }
}
