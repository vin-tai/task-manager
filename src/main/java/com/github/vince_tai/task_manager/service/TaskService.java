package com.github.vince_tai.task_manager.service;

import com.github.vince_tai.task_manager.api.dto.TaskRequest;
import com.github.vince_tai.task_manager.api.dto.TaskResponse;
import com.github.vince_tai.task_manager.domain.entity.Account;
import com.github.vince_tai.task_manager.domain.entity.Task;
import com.github.vince_tai.task_manager.domain.entity.TaskStatus;
import com.github.vince_tai.task_manager.domain.repository.AccountRepository;
import com.github.vince_tai.task_manager.domain.repository.TaskRepository;
import com.github.vince_tai.task_manager.mapping.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final AccountRepository accountRepository;
    private final TaskMapper mapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, AccountRepository accountRepository, TaskMapper mapper) {
        this.taskRepository = taskRepository;
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    public TaskResponse create(TaskRequest request, String username) {
        Task task = mapper.toEntity(request);
        Account author = accountRepository.findByEmail(username).orElseThrow();
        task.setAuthor(author);
        task.setStatus(TaskStatus.CREATED);
        return mapper.toDto(taskRepository.save(task));
    }

    public List<TaskResponse> getTasks(String username) {
        List<Task> tasks = (username != null)
                ? taskRepository.findByAuthorEmail(username.toLowerCase())
                : taskRepository.findAll();
        return tasks.stream()
                .map(mapper::toDto)
                .toList()
                .reversed();
    }
}
