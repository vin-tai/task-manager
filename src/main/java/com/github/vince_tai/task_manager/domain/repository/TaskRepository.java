package com.github.vince_tai.task_manager.domain.repository;

import com.github.vince_tai.task_manager.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAuthorEmail(String username);
    List<Task> findByAuthorEmailAndAssigneeEmail(String author, String assignee);
    List<Task> findByAssigneeEmail(String assignee);
    Optional<Task> findById(long id);
}
