package ru.sber.hack.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CreateTaskRecordDTO {
    private Long recordId;
    private Long taskId;

    @JsonCreator
    public CreateTaskRecordDTO(Long taskId, Long recordId) {
        this.taskId = taskId;
        this.recordId = recordId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
