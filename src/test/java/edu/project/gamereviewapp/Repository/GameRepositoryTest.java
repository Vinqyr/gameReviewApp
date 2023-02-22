package edu.project.gamereviewapp.Repository;

import edu.project.gamereviewapp.Entity.Game;
import edu.project.gamereviewapp.Entity.Review;
import edu.project.gamereviewapp.Enum.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
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
        Game game = new Game("Dota2", "Volvo", Genre.STRATEGY, LocalDate.now(), new ArrayList<Review>());
        Review review = new Review(9.5,"NICE", "DOTA",game);
        List<Review> reviewList =new ArrayList<>();
        reviewList.add(review);
        game.setReviews(reviewList);

        //When
        testGameRepository.save(game);
        //Then
        Optional<Game> optionalGame = testGameRepository.findById(game.getId());
        assertThat(optionalGame).isPresent();
    }

    @Test
    @Sql(value = {"/sql/clear-db.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void findByIdTest() {
        //Given

        List<Review> reviewList = new ArrayList<>();

        Game game = new Game("Dota2", "Volvo", Genre.STRATEGY, LocalDate.now(), reviewList);
        Review review = new Review(9.5,"NICE", "DOTA",game);
        reviewList.add(review);

        //When
        testGameRepository.save(game);
        //Then
        Optional<Game> optionalGame = testGameRepository.findById(game.getId());
        assertThat(optionalGame)
                .hasValueSatisfying(it -> it
                        .getId()
                        .equals(game.getId()));
    }
}