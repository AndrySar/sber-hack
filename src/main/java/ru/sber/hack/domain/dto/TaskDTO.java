package ru.sber.hack.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TaskDTO {
  private long id;
  private String title;
  private int status;
  private String description;
  private Long ownerId;
  private String created;
  private String updated;
  private List<RecordDTO> records;

  public TaskDTO() {

  }

  public TaskDTO(long id, String title, int status, String description, Long ownerId, LocalDateTime created,
          LocalDateTime updated)
  {
    this.id = id;
    this.title = title;
    this.status = status;
    this.description = description;
    this.ownerId = ownerId;
    this.created = created.toString();
    this.updated = updated.toString();
  }

  public long getId() {
    return id;
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

  public Long getOwnerId() {
    return ownerId;
  }

  public String getCreated() {
    return created;
  }

  public String getUpdated() {
    return updated;
  }

  public List<RecordDTO> getRecords() {
    return records;
  }

  public void setRecords(List<RecordDTO> records) {
    this.records = records;
  }
}
