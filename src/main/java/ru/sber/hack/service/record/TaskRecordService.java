package ru.sber.hack.service.record;

import java.util.List;

import ru.sber.hack.domain.dto.CreateTaskRecordDTO;
import ru.sber.hack.domain.dto.RecordDTO;

public interface TaskRecordService {

    void createTaskRecord(CreateTaskRecordDTO dto);

    void createTaskRecords(List<CreateTaskRecordDTO> dtos);
}
