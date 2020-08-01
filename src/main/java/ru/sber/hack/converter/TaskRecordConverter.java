package ru.sber.hack.converter;

import ru.sber.hack.domain.dto.CreateTaskRecordDTO;
import ru.sber.hack.domain.entity.TaskRecordEntity;

public class TaskRecordConverter {

    public static TaskRecordEntity convert(CreateTaskRecordDTO dto) {
        TaskRecordEntity entity = new TaskRecordEntity();
        entity.setTaskId(dto.getTaskId());
        entity.setRecordId(dto.getRecordId());

        return entity;
    }
}
