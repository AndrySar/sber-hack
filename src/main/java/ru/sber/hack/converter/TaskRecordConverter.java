package ru.sber.hack.converter;

import ru.sber.hack.domain.dto.CreateTaskRecordDTO;
import ru.sber.hack.domain.dto.RecordDTO;
import ru.sber.hack.domain.entity.Records;
import ru.sber.hack.domain.entity.TaskRecordEntity;

public class TaskRecordConverter {

    public static TaskRecordEntity convert(CreateTaskRecordDTO dto) {
        TaskRecordEntity entity = new TaskRecordEntity();
        entity.setTaskId(dto.getTaskId());
        entity.setRecordId(dto.getRecordId());

        return entity;
    }

    public static RecordDTO convert(Records records) {
        return new RecordDTO(records.getId(), records.getText(), getFileName(records.getAudioFile()));
    }

    private static String getFileName(String path) {
        String[] arr = path.split("/");
        return arr[arr.length - 1];
    }
}
