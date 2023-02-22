package edu.project.gamereviewapp.DTO;

import edu.project.gamereviewapp.Entity.Game;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReviewRequestDto {
    @NotNull
    private  Long id;
    @NotNull
    @Max(value = 10,message = "It's 10 out of 10 max here")
    private  Double score;
    @NotBlank
    private  String summary;
    @NotBlank
    private  String similarGames;
    @NotNull
    private Game game;
}
