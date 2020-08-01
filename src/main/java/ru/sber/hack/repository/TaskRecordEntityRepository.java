package ru.sber.hack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sber.hack.domain.entity.TaskRecordEntity;

public interface TaskRecordEntityRepository extends JpaRepository<TaskRecordEntity, Long> {
}
