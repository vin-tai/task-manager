package com.github.vince_tai.task_manager.service;

import com.github.vince_tai.task_manager.api.dto.TaskRequest;
import com.github.vince_tai.task_manager.domain.entity.Task;
import com.github.vince_tai.task_manager.domain.entity.TaskStatus;
import com.github.vince_tai.task_manager.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(TaskRequest request, String username) {
        Task task = new Task(
                request.title(),
                request.description(),
                TaskStatus.CREATED,
                username
        );
        return repository.save(task);
    }
}
