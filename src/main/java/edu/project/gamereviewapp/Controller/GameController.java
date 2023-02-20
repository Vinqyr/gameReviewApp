package edu.project.gamereviewapp.Controller;

import edu.project.gamereviewapp.DTO.GameRequestDto;
import edu.project.gamereviewapp.DTO.GameResponseDto;
import edu.project.gamereviewapp.Entity.Game;
import edu.project.gamereviewapp.Enum.Genre;
import edu.project.gamereviewapp.Service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    private final ModelMapper modelMapper;

    @Autowired
    public GameController(GameService gameService, ModelMapper modelMapper) {
        this.gameService = gameService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{gameId}")
    public GameResponseDto getGame(@PathVariable Long gameId) {
//
//        GameResponseDto gameResponseDto = new GameResponseDto();
//        gameResponseDto.setGameName(gameService.getGameById(gameId).getName());
//        gameResponseDto.setDeveloper(gameService.getGameById(gameId).getDeveloper());
//        gameResponseDto.setReviews(gameService.getGameById(gameId).getReviews());
//        gameResponseDto.setReleaseDate(gameService.getGameById(gameId).getReleaseDate());
//        List<Genre> genreList = new ArrayList<>(gameService.getGameById(gameId).getGenre().stream()
//                .map(it -> Genre.parseString(it.name())).collect(Collectors.toList()));
//        gameResponseDto.setGenre(genreList);


        GameResponseDto gameResponseDto = new GameResponseDto(gameService.getGameById(gameId));

        return gameResponseDto;
    }

    @PostMapping("/")
    public void createGame(@RequestBody GameRequestDto gameRequestDTO) {
        gameService.createGame(gameRequestDTO.getGameName(),gameRequestDTO.getDeveloper(),gameRequestDTO.getGenre(),
                gameRequestDTO.getReleaseDate(),gameRequestDTO.getReviews());
    }

    @PutMapping("/{gameId}")
    public GameResponseDto updateGame(@PathVariable Long gameId,@RequestBody GameRequestDto gameRequestDTO) {
        return new GameResponseDto(gameService.updateGame(gameId,modelMapper.map(gameRequestDTO, Game.class)));
    }

    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable Long gameId) {
        gameService.deleteGameById(gameId);

    }
}
