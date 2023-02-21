package edu.project.gamereviewapp.Controller;

import edu.project.gamereviewapp.DTO.ReviewRequestDto;
import edu.project.gamereviewapp.DTO.ReviewResponseDto;
import edu.project.gamereviewapp.Service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final ModelMapper modelMapper;

    @Autowired
    public ReviewController(ReviewService reviewService, ModelMapper modelMapper) {
        this.reviewService = reviewService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<ReviewResponseDto>> findAllByGameId(@PathVariable Long gameId){
        return new ResponseEntity<>(reviewService.findAllByGameId(gameId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDto> save(@RequestBody ReviewRequestDto reviewRequestDTO) {
        return new ResponseEntity<>(reviewService.save(reviewRequestDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable Long id, @RequestBody ReviewRequestDto reviewRequestDTO) {
        return new ResponseEntity<>(reviewService.update(id, reviewRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReviewResponseDto> deleteReviewById(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.deleteReviewById(id), HttpStatus.OK);
    }
}
