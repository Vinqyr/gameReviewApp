CREATE TABLE review
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    score       numeric(2,1) NOT NULL,
    summary VARCHAR(1024) NOT NULL,
    similar_games        VARCHAR(128)  NOT NULL,
    game_id    BIGINT REFERENCES game(id)
)