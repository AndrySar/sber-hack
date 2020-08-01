package ru.sber.hack.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.hack.converter.TaskConverter;
import ru.sber.hack.domain.dto.CreateTaskDTO;
import ru.sber.hack.domain.dto.CreateTaskRecordDTO;
import ru.sber.hack.domain.dto.TaskDTO;
import ru.sber.hack.domain.entity.TaskEntity;
import ru.sber.hack.domain.model.request.CreateTaskRequest;
import ru.sber.hack.domain.model.response.CreateTaskResponse;
import ru.sber.hack.service.record.TaskRecordService;
import ru.sber.hack.service.task.TaskService;

@RestController
public class TaskController {

    private final TaskService taskService;
    private final TaskRecordService taskRecordService;

    public  TaskController(TaskService taskService,
            TaskRecordService taskRecordService) {
        this.taskService = taskService;
        this.taskRecordService = taskRecordService;
    }

    @RequestMapping(path = "/task", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreateTaskResponse createTask(@RequestBody CreateTaskDTO taskDTO) {
        TaskEntity taskEntity = taskService.createTask(taskDTO);
        List<String> records = taskDTO.getRecords();

        if (Objects.nonNull(records) && !records.isEmpty()) {
            List<CreateTaskRecordDTO> enrichRecordDto = records.stream()
                    .map(recordId -> new CreateTaskRecordDTO(taskEntity.getId(), Long.valueOf(recordId)))
                    .collect(Collectors.toList());

            taskRecordService.createTaskRecords(enrichRecordDto);
        }

        return new CreateTaskResponse(taskEntity.getId());
    }

    @RequestMapping(path = "/tasks", method = RequestMethod.GET)
    public List<TaskDTO> getTasks() {
        return taskService.getTasks();
    }

    @RequestMapping(path = "/task/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TaskDTO getTask(@PathVariable final String id) {
        Optional<TaskEntity> optionalTaskEntity = taskService.getTask(Long.valueOf(id));

        if (optionalTaskEntity.isPresent()) {
            TaskEntity taskEntity = optionalTaskEntity.get();

           return TaskConverter.convert(taskEntity);
        }

        return new TaskDTO();
    }

}
