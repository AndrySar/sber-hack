package ru.sber.hack.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTaskDTO {
    private String title;
    private int status;
    private String description;
    private String ownerId;
    private LocalDateTime created;
    private LocalDateTime updated;
    private List<String> records;

    @JsonCreator
    public CreateTaskDTO(@JsonProperty("title") String title,
            @JsonProperty("status") int status,
            @JsonProperty("description") String description,
            @JsonProperty("ownerId") String ownerId,
            @JsonProperty("records") List<String> records)
    {
        this.title = title;
        this.status = status;
        this.description = description;
        this.ownerId = ownerId;
        this.records = records;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public List<String> getRecords() {
        return records;
    }
}
