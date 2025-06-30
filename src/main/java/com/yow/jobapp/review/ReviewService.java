package com.yow.jobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(long companyId);
    Review getReviewById(long companyId, long reviewId);
    Review addReview(long companyId, Review review);
    Review updateReview(long companyId, long reviewId, Review review);
    Boolean deleteReview(long companyId, long reviewId);
}
