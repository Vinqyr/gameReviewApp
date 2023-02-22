package edu.project.gamereviewapp.Service;

import edu.project.gamereviewapp.DTO.GameRequestDto;
import edu.project.gamereviewapp.DTO.GameResponseDto;
import edu.project.gamereviewapp.Entity.Game;
import edu.project.gamereviewapp.Enum.Genre;
import edu.project.gamereviewapp.Repository.GameRepository;
import edu.project.gamereviewapp.exception.GameNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public GameService(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }

    public List<GameResponseDto> findAll(){
        if(gameRepository.findAll().isEmpty())
            throw new GameNotFoundException("Game list is empty");
        List<Game> gameList = gameRepository.findAll();
        return gameList.stream()
                .map(it -> modelMapper.map(it,GameResponseDto.class))
                .collect(Collectors.toList());
    }
    public GameResponseDto findById(Long id){
        Game game = gameRepository
                .findById(id).
                orElseThrow(() -> new GameNotFoundException("Unable to find game by id: " + id));
        return modelMapper.map(game,GameResponseDto.class);
    }

    public List<GameResponseDto> findAllByGenre(Genre genre){
        List<Game> gameList = gameRepository
                .findAllByGenre(genre)
                .orElseThrow(() -> new GameNotFoundException("Game list is empty for this genre :: " + genre.getValue().toUpperCase()));
        return gameList.stream()
                .map(it -> modelMapper.map(it,GameResponseDto.class))
                .collect(Collectors.toList());
    }
    @Transactional
    public GameResponseDto save(GameRequestDto gameRequestDto){
        Game game = gameRepository.save(modelMapper.map(gameRequestDto,Game.class));
        return modelMapper.map(game,GameResponseDto.class);
    }
    @Transactional
    public GameResponseDto update(Long id,GameRequestDto gameRequestDto){
        Game game = gameRepository
                .findById(id)
                .orElseThrow(() -> new GameNotFoundException("Unable to find game by id: " + id));
        game=modelMapper.map(gameRequestDto,Game.class);
        gameRepository.save(game);
        return modelMapper.map(game,GameResponseDto.class);

    }

    @Transactional
    public GameResponseDto deleteGameById (Long id){
        Game game = gameRepository
                .findById(id)
                .orElseThrow(() -> new GameNotFoundException("Unable to find game by id: " + id));
        gameRepository.save(game);
        return modelMapper.map(game,GameResponseDto.class);
    }
}
