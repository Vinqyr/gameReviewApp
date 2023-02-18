package edu.project.gamereviewapp.Repository;

import edu.project.gamereviewapp.Entity.Game;
import edu.project.gamereviewapp.Entity.Review;
import edu.project.gamereviewapp.Enum.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class GameRepositoryTest {

    @Autowired
    private  GameRepository testGameRepository;

    @Test
    void itShouldSelectGamesByGenre() {
        //Given

        //When

        //Then
    }

    @Test
    void saveGameTest() {
        //Given
        List<Genre> genres = new ArrayList<>();
        List<Review> review = new ArrayList<>();

        review.add(new Review());
        genres.add(Genre.STRATEGY);

        Game game = new Game("Dota2", "Volvo", genres, LocalDate.now(), review);
        //When
        testGameRepository.save(game);
        //Then
        Optional<Game> optionalGame = testGameRepository.findById(game.getId());
        assertThat(optionalGame).isPresent();
    }
}