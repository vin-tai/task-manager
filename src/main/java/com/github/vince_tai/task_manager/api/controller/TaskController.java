package com.github.vince_tai.task_manager.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @GetMapping(path = "/api/tasks")
    public ResponseEntity<Void> register() {
        return ResponseEntity.ok().build();
    }
}
