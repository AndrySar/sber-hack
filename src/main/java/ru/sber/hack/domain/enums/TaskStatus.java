package ru.sber.hack.domain.enums;

import java.util.stream.Stream;

public enum TaskStatus {
  /**
   * В бэклоге.
   */
  BACKLOG(0),

  /**
   * В процессе выполнения.
   */
  IN_PROGRESS(1),

  /**
   * На ревью.
   */
  REVIEW(2),

  /**
   * Закрыта.
   */
  CLOSED(3);

  private final int id;

  TaskStatus(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public static TaskStatus of(int id) {
    return Stream.of(values()).filter(status -> status.getId() == id).findFirst().orElse(BACKLOG);
  }

  public static TaskStatus of(String name) {
    return Stream.of(values()).filter(status -> status.name().equals(name)).findFirst().orElse(BACKLOG);
  }
}
