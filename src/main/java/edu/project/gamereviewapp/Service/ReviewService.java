package edu.project.gamereviewapp.Service;

import edu.project.gamereviewapp.DTO.ReviewRequestDto;
import edu.project.gamereviewapp.DTO.ReviewResponseDto;
import edu.project.gamereviewapp.Entity.Review;
import edu.project.gamereviewapp.Repository.ReviewRepository;
import edu.project.gamereviewapp.exception.ReviewNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    public List<ReviewResponseDto> findAll(){
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(it -> modelMapper.map(it,ReviewResponseDto.class))
                .collect(Collectors.toList());
    }


    public List<ReviewResponseDto> findAllByGameId(Long gameId){
        List<Review> reviews = reviewRepository
                .findAllByGameId(gameId)
                .orElseThrow(()-> new ReviewNotFoundException("Reviews not found for this gameId :: "+gameId));
        return reviews.stream()
                .map(it -> modelMapper.map(it,ReviewResponseDto.class))
                .collect(Collectors.toList());
    }

    public ReviewResponseDto findById(Long id){
        Review review = reviewRepository
                .findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found for this id :: " + id));
        return modelMapper.map(review,ReviewResponseDto.class);
    }
    @Transactional
    public ReviewResponseDto save(ReviewRequestDto reviewRequestDto){
        Review review = reviewRepository.save(modelMapper.map(reviewRequestDto, Review.class));
        return modelMapper.map(review,ReviewResponseDto.class);
    }
    @Transactional
    public ReviewResponseDto update(Long id, ReviewRequestDto reviewRequestDto){
        Review review = reviewRepository
                .findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found for this id :: " + id));
        review =reviewRepository.save(modelMapper.map(reviewRequestDto,Review.class));
        return modelMapper.map(review,ReviewResponseDto.class);
    }

    @Transactional
    public ReviewResponseDto deleteReviewById (Long id){
        Review review = reviewRepository
                .findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found for this id :: " + id));
        reviewRepository.deleteById(id);
        return modelMapper.map(review,ReviewResponseDto.class);
    }
}
