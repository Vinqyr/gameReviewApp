package edu.project.gamereviewapp.DTO;

import edu.project.gamereviewapp.Entity.Game;
import edu.project.gamereviewapp.Entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ReviewResponseDto {
    private  Long id;
    private  BigDecimal score;
    private  String summary;
    private  String similarGames;
    private Long gameId;



    public ReviewResponseDto(Review review) {

        id=review.getId();
        score=review.getScore();
        summary=review.getSummary();
        similarGames=review.getSimilarGames();
        gameId=review.getGameId();

    }
}
