DROP TABLE letter IF EXISTS;

CREATE TABLE letter (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    value VARCHAR(20)
);