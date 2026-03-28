package com.github.vince_tai.task_manager.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AssignTaskRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String assignee
) {
}
