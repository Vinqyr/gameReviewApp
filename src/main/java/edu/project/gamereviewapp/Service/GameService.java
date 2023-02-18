package edu.project.gamereviewapp.Service;

import edu.project.gamereviewapp.Entity.Game;
import edu.project.gamereviewapp.Entity.Review;
import edu.project.gamereviewapp.Enum.Genre;
import edu.project.gamereviewapp.Repository.GameRepository;
import edu.project.gamereviewapp.exception.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    public Game getGameById(Long id){
        Game game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Unable to find game by id: " + id));
        return game;
    }
    @Transactional
    public void createGame(String name, String developer, List<Genre> genre, LocalDate releaseDate, List<Review> reviews){

        Game game =new Game(name,developer,genre,releaseDate,reviews);
        gameRepository.save(game);
    }
    @Transactional
    public Game updateGame(Long id,Game updatedGame){
        updatedGame.setId(id);
        return gameRepository.save(updatedGame);
    }

    @Transactional
    public void deleteGameById (Long id){
        gameRepository.delete(getGameById(id));
    }
}
