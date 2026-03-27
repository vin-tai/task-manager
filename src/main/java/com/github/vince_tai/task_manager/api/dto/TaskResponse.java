package com.github.vince_tai.task_manager.api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.vince_tai.task_manager.domain.entity.TaskStatus;

@JsonPropertyOrder({"id", "title", "description", "status", "author"})
public record TaskResponse(
        String id,
        String title,
        String description,
        TaskStatus status,
        String author
) {
}
