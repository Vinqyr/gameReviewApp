package edu.project.gamereviewapp.Controller;

import edu.project.gamereviewapp.DTO.ReviewRequestDto;
import edu.project.gamereviewapp.DTO.ReviewResponseDto;
import edu.project.gamereviewapp.Entity.Review;
import edu.project.gamereviewapp.Service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final ModelMapper modelMapper;

    @Autowired
    public ReviewController(ReviewService reviewService, ModelMapper modelMapper) {
        this.reviewService = reviewService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{reviewId}")
    public ReviewResponseDto getReview(@PathVariable Long reviewId) {
        return new ReviewResponseDto(reviewService.getReviewById(reviewId));
    }

    @PostMapping("/")
    public void createReview(@RequestBody ReviewRequestDto reviewRequestDTO) {
        reviewService.createReview(reviewRequestDTO.getScore(), reviewRequestDTO.getSummary(),
                reviewRequestDTO.getSimilarGames(),reviewRequestDTO.getGameId());
    }

    @PutMapping("/{reviewId}")
    public ReviewResponseDto updateReview(@PathVariable Long reviewId, @RequestBody ReviewRequestDto reviewRequestDTO) {
        return new ReviewResponseDto(reviewService.updateReview(reviewId,modelMapper.map(reviewRequestDTO, Review.class)));
    }

    @DeleteMapping("/{gameId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReviewById(reviewId);
    }
}
