package com.github.vince_tai.task_manager.domain.repository;

import com.github.vince_tai.task_manager.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAuthor(String author);
}
