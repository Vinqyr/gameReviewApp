package edu.project.gamereviewapp.DTO;

import edu.project.gamereviewapp.Entity.Review;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponseDto {
    private  Long id;
    private  Double score;
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
