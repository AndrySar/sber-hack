-- auto-generated definition

--changeset msa:add_task_record_table
CREATE TABLE IF NOT EXISTS task_record
(
    id               BIGSERIAL                NOT NULL PRIMARY KEY,
    task_id          BIGINT                   NOT NULL REFERENCES task (id),
    record_id        BIGINT                   NOT NULL,
    created          TIMESTAMP                NOT NULL DEFAULT current_timestamp
);