package com.github.vince_tai.task_manager.api.dto;

import com.github.vince_tai.task_manager.domain.entity.TaskStatus;

public record StatusRequest (
        TaskStatus status
){}
