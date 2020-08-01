package ru.sber.hack.converter;

import java.time.LocalDateTime;

import ru.sber.hack.domain.dto.CreateTaskDTO;
import ru.sber.hack.domain.dto.TaskDTO;
import ru.sber.hack.domain.entity.TaskEntity;
import ru.sber.hack.domain.enums.TaskStatus;

public class TaskConverter {

    public static TaskEntity convert(TaskDTO taskDTO) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setStatus(TaskStatus.of(taskDTO.getStatus()));
        taskEntity.setTitle(taskDTO.getTitle());
        taskEntity.setDescription(taskDTO.getDescription());
        taskEntity.setOwnerId(Long.valueOf(taskDTO.getOwnerId()));
        return taskEntity;
    }

    public static TaskEntity convert(CreateTaskDTO taskDTO) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setStatus(TaskStatus.of(taskDTO.getStatus()));
        taskEntity.setTitle(taskDTO.getTitle());
        taskEntity.setDescription(taskDTO.getDescription());
        taskEntity.setOwnerId(Long.valueOf(taskDTO.getOwnerId()));
        taskEntity.setCreated(LocalDateTime.now());
        taskEntity.setUpdated(LocalDateTime.now());
        return taskEntity;
    }

    public static TaskDTO convert(TaskEntity taskEntity) {
        return new TaskDTO(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getStatus().getId(),
                taskEntity.getDescription(),
                taskEntity.getOwnerId(),
                taskEntity.getCreated(),
                taskEntity.getUpdated());
    }
}
