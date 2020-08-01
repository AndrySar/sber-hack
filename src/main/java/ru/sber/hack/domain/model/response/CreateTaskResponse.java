package ru.sber.hack.domain.model.response;

public class CreateTaskResponse {
    private long taskId;

    public CreateTaskResponse(long taskId) {
        this.taskId = taskId;
    }

    public long getTaskId() {
        return taskId;
    }

    @Override
    public String toString() {
        return "{taskId=" + taskId + '}';
    }
}
