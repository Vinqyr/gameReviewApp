package edu.project.gamereviewapp.Service;

import edu.project.gamereviewapp.Entity.Game;
import edu.project.gamereviewapp.Entity.Review;
import edu.project.gamereviewapp.Repository.ReviewRepository;
import edu.project.gamereviewapp.exception.ReviewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    public Review getReviewById(Long id){
        return reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Unable to find review by id: "+id));
    }
    @Transactional
    public void createReview(BigDecimal score, String summary, String similarGames, Long gameId){
        Review review =new Review(score,summary,similarGames,gameId);
        reviewRepository.save(review);
    }
    @Transactional
    public Review updateReview(Long id,Review updatedReview){
        updatedReview.setId(id);
        return reviewRepository.save(updatedReview);
    }

    @Transactional
    public void deleteReviewById (Long id){
        reviewRepository.delete(getReviewById(id));
    }
}
