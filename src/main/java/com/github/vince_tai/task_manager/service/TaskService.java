package com.github.vince_tai.task_manager.service;

import com.github.vince_tai.task_manager.api.dto.TaskRequest;
import com.github.vince_tai.task_manager.api.dto.TaskResponse;
import com.github.vince_tai.task_manager.domain.entity.Task;
import com.github.vince_tai.task_manager.domain.entity.TaskStatus;
import com.github.vince_tai.task_manager.domain.repository.TaskRepository;
import com.github.vince_tai.task_manager.mapping.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;

    @Autowired
    public TaskService(TaskRepository repository,  TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TaskResponse create(TaskRequest request, String username) {
        Task task = mapper.toEntity(request);
        task.setAuthor(username);
        task.setStatus(TaskStatus.CREATED);
        Task newTask = repository.save(task);
        return mapper.toDto(newTask);
    }

    public List<TaskResponse> getTasks(String author) {
        List<Task> tasks = (author != null)
                ? repository.findByAuthor(author.toLowerCase())
                : repository.findAll();
        return processTasks(tasks);
    }

    private List<TaskResponse> processTasks(List<Task> tasks) {
        return tasks.stream()
                .map(mapper::toDto)
                .toList()
                .reversed();
    }
}
