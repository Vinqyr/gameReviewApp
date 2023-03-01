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
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GameService testGameService;


    @BeforeEach
    void setUp() {
        testGameService = new GameService(gameRepository, modelMapper);
    }


    @Test
    void shouldSave() {
//        //Given
        Game gameToSave = Game.builder()
                .name("WoW")
                .developer("Blizzard")
                .genre(Genre.HORROR)
                .releaseDate(LocalDate.now())
                .reviews(new ArrayList<>())
                .build();
        Game gameAfterSave = Game.builder()
                .id(1L)
                .name("WoW")
                .developer("Blizzard")
                .genre(Genre.HORROR)
                .releaseDate(LocalDate.now())
                .reviews(new ArrayList<>())
                .build();
        GameResponseDto expectedGame = modelMapper.map(gameAfterSave, GameResponseDto.class);
        GameRequestDto requestDto = modelMapper.map(gameToSave, GameRequestDto.class);

        when(gameRepository.save(any(Game.class))).thenReturn(gameToSave);

        //When
        GameResponseDto actual = testGameService.save(requestDto);
        //Then
        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expectedGame);
        verify(gameRepository, times(1)).save(any(Game.class));
    }

    @Test
    void shouldFindAll() {

        Game foundGame1 = Game.builder()
                .id(1L)
                .name("WoW")
                .developer("Blizzard")
                .genre(Genre.HORROR)
                .releaseDate(LocalDate.of(2014, 5, 15))
                .reviews(new ArrayList<>())
                .build();
        Game foundGame2 = Game.builder()
                .id(2L)
                .name("NotWoW")
                .developer("NotBlizzard")
                .genre(Genre.STRATEGY)
                .releaseDate(LocalDate.of(2017, 5, 22))
                .reviews(new ArrayList<>())
                .build();
        List<Game> gameList = new ArrayList<>();
        gameList.add(foundGame1);
        gameList.add(foundGame2);
        when(gameRepository.findAll()).thenReturn(gameList);

        List<GameResponseDto> expectedList = gameList.stream()
                .map(it -> modelMapper.map(it, GameResponseDto.class))
                .collect(Collectors.toList());

        List<GameResponseDto> actualList = testGameService.findAll();

        assertThat(expectedList).usingRecursiveComparison().isEqualTo(actualList);
        assertEquals(expectedList.size(), 2);
        verify(gameRepository, times(1)).findAll();
    }

    @Test
    void shouldNotFindByIdAndThrowException() {
        //given
        Long nonExistentId = 0L;
        Optional<Game> nonExistentGameEntity = Optional.empty();

        when(gameRepository.findById(nonExistentId)).thenReturn(nonExistentGameEntity);
        //when
        assertThrows(
                GameNotFoundException.class,
                () -> testGameService.findById(nonExistentId));
        //then
        verify(gameRepository, times(1)).findById(nonExistentId);
    }

    @Test
    void shouldFindById() {
        //given
        Long existingId = 1L;
        Optional<Game> existingGameEntity = Optional.of(Game.builder()
                .id(existingId)
                .name("DOS2")
                .developer("Larian")
                .reviews(new ArrayList<>())
                .genre(Genre.STRATEGY)
                .releaseDate(LocalDate.of(2002, 12, 12))
                .build());

        GameResponseDto expectedGame = modelMapper.map(existingGameEntity.get(), GameResponseDto.class);

        when(gameRepository.findById(existingId)).thenReturn(existingGameEntity);
        //when
        GameResponseDto actualGame = testGameService.findById(existingId);
        //then
        assertThat(expectedGame).usingRecursiveComparison().isEqualTo(actualGame);
        verify(gameRepository,times(1)).findById(existingId);
    }

    @Test
    void shouldEitherDeleteByIdOrThrowException() {
        //given
        Long nonExistentId = 0L;
        Optional<Game> nonExistentGameEntity = Optional.empty();

        Long existingId = 1L;
        Optional<Game> existingGameEntity = Optional.of(Game.builder()
                .id(existingId)
                .name("DOS2")
                .developer("Larian")
                .reviews(new ArrayList<>())
                .genre(Genre.STRATEGY)
                .releaseDate(LocalDate.of(2002, 12, 12))
                .build());
        GameResponseDto expectedGame = modelMapper.map(existingGameEntity.get(),GameResponseDto.class);

        when(gameRepository.findById(nonExistentId)).thenReturn(nonExistentGameEntity);
        when(gameRepository.findById(existingId)).thenReturn(existingGameEntity);
        doNothing().when(gameRepository).deleteById(existingId);
        //when
        assertThrows(
                GameNotFoundException.class,
                () -> testGameService.deleteGameById(nonExistentId));
        GameResponseDto actualGame = testGameService.deleteGameById(existingId);
        //then
        assertThat(expectedGame).usingRecursiveComparison().isEqualTo(actualGame);
        verify(gameRepository, times(1)).findById(existingId);
        verify(gameRepository, times(1)).deleteById(existingId);
        verify(gameRepository, times(1)).findById(nonExistentId);
        verify(gameRepository, times(0)).deleteById(nonExistentId);


    }

    @Test
    void shouldUpdate() {
        //given
        Long existingId = 1L;
        Optional<Game> existingGameEntity = Optional.of(Game.builder()
                .id(existingId)
                .name("DOS2")
                .developer("Larian")
                .reviews(new ArrayList<>())
                .genre(Genre.STRATEGY)
                .releaseDate(LocalDate.of(2002, 12, 12))
                .build());
        GameRequestDto updateRequestDto = GameRequestDto.builder()
                .name("DOS2")
                .developer("Larian")
                .reviews(new ArrayList<>())
                .genre(Genre.RPG)
                .releaseDate(LocalDate.of(2018,12,15))
                .build();
        Game gameForSave = modelMapper.map(updateRequestDto, Game.class);
        Game gameAfterSave = modelMapper.map(gameForSave,Game.class);
        gameAfterSave.setId(existingId);

        GameResponseDto expectedGame = modelMapper.map(gameForSave,GameResponseDto.class);
        expectedGame.setId(existingId);

        when(gameRepository.findById(existingId)).thenReturn(existingGameEntity);
        when(gameRepository.save(any(Game.class))).thenReturn(gameAfterSave);
        doReturn(gameAfterSave).when(gameRepository).save(gameForSave);

        //when
        GameResponseDto actualGame = testGameService.update(existingId, updateRequestDto);
        //then
        assertThat(expectedGame).usingRecursiveComparison().isEqualTo(actualGame);
        verify(gameRepository,times(1)).findById(existingId);
        verify(gameRepository,times(1)).save(any(Game.class));

    }
}