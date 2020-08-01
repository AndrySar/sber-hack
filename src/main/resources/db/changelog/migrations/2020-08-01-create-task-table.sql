-- auto-generated definition

--changeset msa:add_task_table
CREATE TABLE IF NOT EXISTS task
(
    id               BIGSERIAL                NOT NULL PRIMARY KEY,
    title            VARCHAR(500)             NOT NULL,
    status           VARCHAR(256)             NOT NULL,
    description      TEXT,
    owner_id         BIGINT                   NOT NULL,
    created          TIMESTAMP                NOT NULL DEFAULT current_timestamp,
    updated          TIMESTAMP                NOT NULL DEFAULT current_timestamp
);