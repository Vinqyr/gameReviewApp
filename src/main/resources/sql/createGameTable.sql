CREATE TABLE game
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name       VARCHAR(64) NOT NULL,
    developer VARCHAR(64) NOT NULL,
    genre        VARCHAR(32)  NOT NULL,
    release_date     DATE
)