package com.github.vince_tai.task_manager.mapping;

import com.github.vince_tai.task_manager.api.dto.TaskRequest;
import com.github.vince_tai.task_manager.api.dto.TaskResponse;
import com.github.vince_tai.task_manager.domain.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskRequest taskRequest);

    @Mapping(source="author.email", target="author")
    @Mapping(source="assignee.email", target="assignee", defaultValue = "none")
    TaskResponse toDto(Task task);
}
