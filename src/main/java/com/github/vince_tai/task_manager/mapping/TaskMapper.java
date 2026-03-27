package com.github.vince_tai.task_manager.mapping;

import com.github.vince_tai.task_manager.api.dto.TaskRequest;
import com.github.vince_tai.task_manager.api.dto.TaskResponse;
import com.github.vince_tai.task_manager.domain.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toEntity(TaskRequest taskRequest);
    TaskResponse toDto(Task task);
}
