package com.github.vince_tai.task_manager.api.controller;

import com.github.vince_tai.task_manager.api.dto.AssignTaskRequest;
import com.github.vince_tai.task_manager.api.dto.StatusRequest;
import com.github.vince_tai.task_manager.api.dto.TaskRequest;
import com.github.vince_tai.task_manager.api.dto.TaskResponse;
import com.github.vince_tai.task_manager.security.AccountAdapter;
import com.github.vince_tai.task_manager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskService service;

    @PostMapping(path = "/api/tasks")
    public ResponseEntity<TaskResponse> createTask(
            @RequestBody @Valid TaskRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        TaskResponse response = service.create(request, jwt.getSubject());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(path = "/api/tasks/{taskId}/assign")
    public ResponseEntity<TaskResponse> assignTask(
            @PathVariable long taskId,
            @RequestBody @Valid AssignTaskRequest request,
            @AuthenticationPrincipal Jwt jwt
            ) {
        return ResponseEntity.ok().body(service.assign(request, taskId, jwt.getSubject()));
    }

    @PutMapping(path ="/api/tasks/{taskId}/status")
    public ResponseEntity<TaskResponse> updateStatus(
            @PathVariable long taskId,
            @RequestBody @Valid StatusRequest request,
            @AuthenticationPrincipal Jwt jwt
    ){
        return ResponseEntity.ok().body(service.updateStatus(request, taskId, jwt.getSubject()));
    }

    @GetMapping(path = "/api/tasks")
    public ResponseEntity<List<TaskResponse>> getTasks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String assignee
    ) {
        return ResponseEntity.ok().body(service.getTasks(author, assignee));
    }
}
