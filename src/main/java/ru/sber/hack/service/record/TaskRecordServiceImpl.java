package ru.sber.hack.service.record;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import ru.sber.hack.converter.TaskRecordConverter;
import ru.sber.hack.domain.dto.CreateTaskRecordDTO;
import ru.sber.hack.domain.dto.RecordDTO;
import ru.sber.hack.domain.entity.Records;
import ru.sber.hack.domain.entity.TaskRecordEntity;
import ru.sber.hack.repository.JdbcTaskRecordsRepository;
import ru.sber.hack.repository.TaskRecordEntityRepository;

@Service
public class TaskRecordServiceImpl implements TaskRecordService {

    private TaskRecordEntityRepository taskRecordEntityRepository;
    private JdbcTaskRecordsRepository jdbcTaskRecordsRepository;

    public TaskRecordServiceImpl(TaskRecordEntityRepository taskRecordEntityRepository,
            JdbcTaskRecordsRepository jdbcTaskRecordsRepository) {
        this.taskRecordEntityRepository = taskRecordEntityRepository;
        this.jdbcTaskRecordsRepository = jdbcTaskRecordsRepository;
    }

    public void createTaskRecord(CreateTaskRecordDTO dto) {
        TaskRecordEntity entity = TaskRecordConverter.convert(dto);

        taskRecordEntityRepository.save(entity);
    }

    public void createTaskRecords(List<CreateTaskRecordDTO> dtos) {
        List<TaskRecordEntity> entities = dtos.stream()
                .map(TaskRecordConverter::convert)
                .collect(Collectors.toList());

        taskRecordEntityRepository.saveAll(entities);
    }

    public List<RecordDTO> getRecordsByTaskId(Long taskId) {
        return jdbcTaskRecordsRepository.findRecordsByTaskId(taskId).stream()
                .map(TaskRecordConverter::convert)
                .collect(Collectors.toList());
    }
}
