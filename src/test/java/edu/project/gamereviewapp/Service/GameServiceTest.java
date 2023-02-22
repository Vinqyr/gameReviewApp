package edu.project.gamereviewapp.Service;

import edu.project.gamereviewapp.DTO.GameRequestDto;
import edu.project.gamereviewapp.DTO.GameResponseDto;
import edu.project.gamereviewapp.Entity.Game;
import edu.project.gamereviewapp.Enum.Genre;
import edu.project.gamereviewapp.Repository.GameRepository;
import edu.project.gamereviewapp.exception.GameNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;
    @Mock
    private ModelMapper modelMapper;

    private GameService testGameService;


    @BeforeEach
    void setUp(){
        testGameService = new GameService(gameRepository,modelMapper);
    }



    @Test
    void shouldSave() {
        //Given
        Game gameBeforeSave= new Game("WoW","Blizzard", Genre.HORROR
                , LocalDate.now(),new ArrayList<>());
        Game gameAfterSave= new Game(1L,"WoW","Blizzard", Genre.HORROR
                , LocalDate.now(),new ArrayList<>());
        GameResponseDto gameResponseDto = Mockito.mock(GameResponseDto.class);
        GameRequestDto gameRequestDto= Mockito.mock(GameRequestDto.class);

        when(modelMapper.map(gameRequestDto,Game.class)).thenReturn(gameBeforeSave);
        when(gameRepository.save(gameBeforeSave)).thenReturn(gameAfterSave);
        when(modelMapper.map(gameAfterSave,GameResponseDto.class)).thenReturn(gameResponseDto);
        //When
        testGameService.save(gameRequestDto);
        //Then
        verify(modelMapper).map(gameRequestDto,Game.class);
        verify(gameRepository).save(gameBeforeSave);
        verify(modelMapper).map(gameAfterSave,GameResponseDto.class);
    }
    @Test
    void shouldFindAll() {

        Game game = Mockito.mock(Game.class);
        GameResponseDto gameResponseDto = mock(GameResponseDto.class);
        List<Game> gameList = new ArrayList<>();
        gameList.add(game);
        when(gameRepository.findAll()).thenReturn(gameList);
        when(modelMapper.map(game,GameResponseDto.class)).thenReturn(gameResponseDto);

        testGameService.findAll();

        verify(gameRepository).findAll();
        gameList.stream().map(it -> verify(modelMapper.map(it,GameResponseDto.class)));
    }

    @Test
    void shouldNotFindByIdAndThrowException() {
        //given
        Long nonExistentId = 0L;
        Optional<Game> nonExistentGameEntity = Optional.empty();

        when(gameRepository.findById(nonExistentId)).thenReturn(nonExistentGameEntity);
        //when

        //then
        assertThrows(
                GameNotFoundException.class,
                () -> testGameService.findById(nonExistentId));
    }

    @Test
    void shouldFindById() {
        //given
        Long existingId = 1L;
        Optional<Game> existingGameEntity = Optional.of(mock(Game.class));
        GameResponseDto gameResponseDto = mock(GameResponseDto.class);

        when(gameRepository.findById(existingId)).thenReturn(existingGameEntity);
        when(modelMapper.map(existingGameEntity.get(),GameResponseDto.class)).thenReturn(gameResponseDto);
        //when
        testGameService.findById(existingId);
        //then
        verify(gameRepository).findById(existingId);
        verify(modelMapper).map(existingGameEntity.get(),GameResponseDto.class);
    }

    @Test
    void shouldEitherDeleteByIdOrThrowException() {
        //given
        Long nonExistentId = 0L;
        Optional<Game> nonExistentMatchEntity = Optional.empty();

        Long existingId = 1L;
        Optional<Game> existingGameEntity = Optional.of(mock(Game.class));
        GameResponseDto gameResponseDto = mock(GameResponseDto.class);

        when(gameRepository.findById(nonExistentId)).thenReturn(nonExistentMatchEntity);
        when(gameRepository.findById(existingId)).thenReturn(existingGameEntity);
        when(modelMapper.map(existingGameEntity.get(),GameResponseDto.class)).thenReturn(gameResponseDto);
        //when
        testGameService.deleteGameById(existingId);
        //then
        verify(gameRepository).findById(existingId);
        verify(gameRepository).delete(existingGameEntity.get());
        verify(modelMapper).map(existingGameEntity.get(),GameResponseDto.class);

        assertThrows(
                GameNotFoundException.class,
                () -> testGameService.deleteGameById(nonExistentId));

    }

    @Test
    void shouldEitherUpdateOrThrowException() {
        //given
        Long nonExistentId = 0L;
        Optional<Game> nonExistentGameEntity = Optional.empty();

        Long existingId = 1L;
        Optional<Game> existingGameEntity = Optional.of(mock(Game.class));
        GameRequestDto gameRequestDto = mock(GameRequestDto.class);
        GameResponseDto gameResponseDto = mock(GameResponseDto.class);
        Game game = Mockito.mock(Game.class);

        when(gameRepository.findById(nonExistentId)).thenReturn(nonExistentGameEntity);
        when(gameRepository.findById(existingId)).thenReturn(existingGameEntity);
        when(modelMapper.map(gameRequestDto,Game.class)).thenReturn(game);
        when(gameRepository.save(game)).thenReturn(game);
        when(modelMapper.map(game,GameResponseDto.class)).thenReturn(gameResponseDto);

        //when
        testGameService.update(existingId, gameRequestDto);
        //then
        verify(gameRepository).findById(existingId);
        verify(gameRepository).save(game);
        verify(modelMapper).map(gameRequestDto,Game.class);
        verify(modelMapper).map(game,GameResponseDto.class);

        assertThrows(
                GameNotFoundException.class,
                () -> testGameService.deleteGameById(nonExistentId));
    }
}