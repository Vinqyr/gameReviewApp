package edu.project.gamereviewapp.DTO;

import edu.project.gamereviewapp.Entity.Game;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReviewRequestDto {
    private  Long id;
    private  Double score;
    private  String summary;
    private  String similarGames;
    private Game game;
}
