package com.github.vince_tai.task_manager.service;

import com.github.vince_tai.task_manager.api.dto.AssignTaskRequest;
import com.github.vince_tai.task_manager.api.dto.StatusRequest;
import com.github.vince_tai.task_manager.api.dto.TaskRequest;
import com.github.vince_tai.task_manager.api.dto.TaskResponse;
import com.github.vince_tai.task_manager.domain.entity.Account;
import com.github.vince_tai.task_manager.domain.entity.Task;
import com.github.vince_tai.task_manager.domain.entity.TaskStatus;
import com.github.vince_tai.task_manager.domain.repository.AccountRepository;
import com.github.vince_tai.task_manager.domain.repository.TaskRepository;
import com.github.vince_tai.task_manager.mapping.TaskMapper;
import com.github.vince_tai.task_manager.security.AccountAdapter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final AccountRepository accountRepository;
    private final TaskMapper mapper;

    @Autowired
    public TaskService(
            TaskRepository taskRepository,
            AccountRepository accountRepository,
            TaskMapper mapper
    ) {
        this.taskRepository = taskRepository;
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Transactional
    public TaskResponse create(TaskRequest request, String username) {
        Task task = mapper.toEntity(request);
        Account author = accountRepository.findByEmail(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        task.setAuthor(author);
        task.setStatus(TaskStatus.CREATED);
        return mapper.toDto(taskRepository.save(task));
    }

    @Transactional
    public TaskResponse assign(AssignTaskRequest request, long taskId, AccountAdapter accountAdapter) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        if (!task.getAuthor().getEmail().equals(accountAdapter.getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to assign this task");
        }

        String username = request.assignee();
        Account assignee = accountRepository.findByEmail(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        task.setAssignee(assignee);
        return mapper.toDto(taskRepository.save(task));
    }

    @Transactional
    public TaskResponse updateStatus(StatusRequest request, long taskId, AccountAdapter accountAdapter) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        if (!task.getAuthor().getEmail().equals(accountAdapter.getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not authorized to assign this task");
        }
        task.setStatus(request.status());
        return mapper.toDto(taskRepository.save(task));
    }

    @Transactional
    public List<TaskResponse> getTasks(String author, String assignee) {
        List<Task> tasks;

        boolean hasAuthor = author != null;
        boolean hasAssignee = assignee != null;

        if (hasAuthor && hasAssignee) {
            tasks = taskRepository.findByAuthorEmailAndAssigneeEmail(author.toLowerCase(), assignee.toLowerCase());
        } else if (hasAuthor) {
            tasks = taskRepository.findByAuthorEmail(author.toLowerCase());
        } else if (hasAssignee) {
            tasks = taskRepository.findByAssigneeEmail(assignee.toLowerCase());
        } else {
            tasks = taskRepository.findAll();
        }

        return tasks.stream()
                .map(mapper::toDto)
                .toList()
                .reversed();
    }
}
