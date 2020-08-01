package ru.sber.hack.service.task;

import java.util.List;
import java.util.Optional;

import ru.sber.hack.domain.dto.CreateTaskDTO;
import ru.sber.hack.domain.dto.TaskDTO;
import ru.sber.hack.domain.entity.TaskEntity;

public interface TaskService {

  TaskEntity createTask(CreateTaskDTO task);

  void updateTask(TaskDTO task);

  List<TaskDTO> getTasks();

  Optional<TaskEntity> getTask(Long id);
}
