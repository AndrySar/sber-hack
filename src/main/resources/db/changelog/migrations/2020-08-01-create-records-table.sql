-- auto-generated definition

--changeset msa:add_task_record_table
CREATE TABLE IF NOT EXISTS records
(
    id               BIGSERIAL                NOT NULL PRIMARY KEY,
    path             VARCHAR(500),
    text             VARCHAR(500)
);