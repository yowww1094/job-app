package com.yow.jobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable long companyId){
        List<Review> reviews = reviewService.getAllReviews(companyId);
        if (reviews != null) return new ResponseEntity<>(reviews, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable long companyId, @PathVariable long reviewId){
        Review review = reviewService.getReviewById(companyId, reviewId);
        if (review != null) return new ResponseEntity<>(review, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@PathVariable long companyId, @RequestBody Review review){
        Review newReview = reviewService.addReview(companyId, review);
        if (newReview != null) return  new ResponseEntity<>(newReview, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable long companyId, @PathVariable long reviewId, @RequestBody Review review){
        Review updatedReview = reviewService.updateReview(companyId, reviewId, review);
        if (updatedReview != null) return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable long companyId, @PathVariable long reviewId){
        Boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
        if (isReviewDeleted) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
