package ru.sber.hack.service.task;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import ru.sber.hack.converter.TaskConverter;
import ru.sber.hack.domain.dto.CreateTaskDTO;
import ru.sber.hack.domain.dto.TaskDTO;
import ru.sber.hack.domain.entity.TaskEntity;
import ru.sber.hack.repository.TaskEntityRepository;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskEntityRepository taskEntityRepository;

    public TaskServiceImpl(TaskEntityRepository taskEntityRepository) {
        this.taskEntityRepository = taskEntityRepository;
    }

    public TaskEntity createTask(CreateTaskDTO task) {
        TaskEntity taskEntity = TaskConverter.convert(task);
        return taskEntityRepository.save(taskEntity);
    }

    public void updateTask(TaskDTO task) {

    }

    public List<TaskDTO> getTasks() {
        return taskEntityRepository.findAll().stream()
                .map(TaskConverter::convert)
                .collect(Collectors.toList());
    }

    public Optional<TaskEntity> getTask(Long id) {
        return taskEntityRepository.findById(id);
    }
}
