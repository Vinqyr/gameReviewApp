package edu.project.gamereviewapp.DTO;

import edu.project.gamereviewapp.Entity.Game;
import edu.project.gamereviewapp.Entity.Review;
import edu.project.gamereviewapp.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class GameResponseDto {
    private String gameName;
    private String developer;
    private List<Genre> genre;
    private OffsetDateTime releaseDate;
    private List<Review> reviews;
    public GameResponseDto(Game game) {
        gameName = game.getName();
        developer = game.getDeveloper();
        genre = game.getGenre();
        releaseDate = game.getReleaseDate();

        reviews = game.getReviews();
    }
}
