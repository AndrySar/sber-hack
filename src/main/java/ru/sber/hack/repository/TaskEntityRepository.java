package ru.sber.hack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.hack.domain.entity.TaskEntity;

@Repository
public interface TaskEntityRepository extends JpaRepository<TaskEntity, Long> {

}
