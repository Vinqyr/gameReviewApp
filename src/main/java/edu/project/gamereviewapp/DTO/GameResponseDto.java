package edu.project.gamereviewapp.DTO;

import edu.project.gamereviewapp.Entity.Game;
import edu.project.gamereviewapp.Entity.Review;
import edu.project.gamereviewapp.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class GameResponseDto {
    private Long id;
    private String name;
    private String developer;
    private Genre genre;
    private LocalDate releaseDate;
    private List<Review> reviews;
    public GameResponseDto(Game game) {
        id = game.getId();
        name = game.getName();
        developer = game.getDeveloper();

        genre = game.getGenre();
        releaseDate = game.getReleaseDate();

        reviews = game.getReviews();
    }
}
