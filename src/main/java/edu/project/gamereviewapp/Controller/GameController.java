package edu.project.gamereviewapp.Controller;

import edu.project.gamereviewapp.DTO.GameRequestDto;
import edu.project.gamereviewapp.DTO.GameResponseDto;
import edu.project.gamereviewapp.Enum.Genre;
import edu.project.gamereviewapp.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;


    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;

    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<GameResponseDto>> findAllByGenre(@PathVariable Genre genre){
        return new ResponseEntity<>(gameService.findAllByGenre(genre),HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<GameResponseDto>> findAll(){
        return new ResponseEntity<>(gameService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(gameService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GameResponseDto> save(@RequestBody GameRequestDto gameRequestDTO) {
        return new ResponseEntity<>(gameService.save(gameRequestDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponseDto> updateGame(@PathVariable Long id, @RequestBody GameRequestDto gameRequestDTO) {
        return new ResponseEntity<>(gameService.update(id, gameRequestDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GameResponseDto> deleteGame(@PathVariable Long id) {
        return new ResponseEntity<>(gameService.deleteGameById(id),HttpStatus.OK);
    }
}
