package ru.sber.hack.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.hack.client.recognition.TextRecognitionClient;
import ru.sber.hack.converter.TaskConverter;
import ru.sber.hack.domain.dto.CreateTaskDTO;
import ru.sber.hack.domain.dto.CreateTaskRecordDTO;
import ru.sber.hack.domain.dto.RecordDTO;
import ru.sber.hack.domain.dto.TaskDTO;
import ru.sber.hack.domain.entity.TaskEntity;
import ru.sber.hack.domain.model.response.CreateTaskResponse;
import ru.sber.hack.service.record.TaskRecordService;
import ru.sber.hack.service.task.TaskService;

@RestController
public class TaskController {

    private final static Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;
    private final TaskRecordService taskRecordService;
    private final TextRecognitionClient textRecognitionClient;

    public  TaskController(TaskService taskService,
            TaskRecordService taskRecordService,
            TextRecognitionClient textRecognitionClient) {
        this.taskService = taskService;
        this.taskRecordService = taskRecordService;
        this.textRecognitionClient = textRecognitionClient;
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

    @RequestMapping(path = "/task/{id}", method = RequestMethod.GET, produces = { "application/json;**charset=UTF-8**" })
    public ResponseEntity getTask(@PathVariable final String id)
            throws JsonProcessingException, UnsupportedEncodingException
    {
        Optional<TaskEntity> optionalTaskEntity = taskService.getTask(Long.valueOf(id));

        if (optionalTaskEntity.isPresent()) {
            TaskEntity taskEntity = optionalTaskEntity.get();

            TaskDTO taskDTO = TaskConverter.convert(taskEntity);

            List<RecordDTO> recordDTOS = taskRecordService.getRecordsByTaskId(taskEntity.getId());

            taskDTO.setRecords(recordDTOS);

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(taskDTO);


            log.info(json);
            return ResponseEntity.status(HttpStatus.OK).body(json.getBytes("UTF-8"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with id=" + id + " does not found");
    }

}
