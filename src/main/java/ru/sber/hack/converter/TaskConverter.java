package ru.sber.hack.converter;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
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
                taskEntity.getUpdated(),
                getAudioDescription(taskEntity.getAudioPath()));
    }

    private static String getAudioDescription(String path) {
        return Optional.ofNullable(path)
                .filter(StringUtils::isNotBlank)
                .map(TaskConverter::getFileName)
                .orElse("");
    }

    private static String getFileName(String path) {
        String[] arr = path.split("/");
        return arr[arr.length - 1];
    }
}
