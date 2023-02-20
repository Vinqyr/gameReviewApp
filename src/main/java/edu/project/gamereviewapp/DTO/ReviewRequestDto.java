package edu.project.gamereviewapp.DTO;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReviewRequestDto {
    private  Long id;
    private  BigDecimal score;
    private  String summary;
    private  String similarGames;
    private Long gameId;
}
