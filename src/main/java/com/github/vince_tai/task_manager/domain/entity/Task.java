package com.github.vince_tai.task_manager.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private String status;

    private String author;

    protected Task() {}
    public Task(String title, String description, String status, String author) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.author = author;
    }
}
