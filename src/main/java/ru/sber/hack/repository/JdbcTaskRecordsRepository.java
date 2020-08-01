package ru.sber.hack.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sber.hack.domain.entity.Records;

@Repository
public class JdbcTaskRecordsRepository {

    private static final String FIND_RECORDS_BY_TASK_ID = "SELECT r.id AS recordId, r.path AS path, r.text AS text "
            + "FROM task t "
            + "INNER JOIN task_record tr ON t.id = tr.task_id "
            + "INNER JOIN records r ON tr.record_id = r.id "
            + "WHERE t.id = :taskId;";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTaskRecordsRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Records> findRecordsByTaskId(Long taskId) {
        return jdbcTemplate.query(
                FIND_RECORDS_BY_TASK_ID,
                new MapSqlParameterSource()
                        .addValue("taskId", taskId),
                (rs, rowNum) -> new Records(
                        rs.getString("recordId"),
                        rs.getString("path"),
                        rs.getString("text")
                )
        );
    }
}
