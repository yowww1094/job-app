package com.yow.jobapp.review.Impl;

import com.yow.jobapp.company.Company;
import com.yow.jobapp.company.CompanyService;
import com.yow.jobapp.review.Review;
import com.yow.jobapp.review.ReviewController;
import com.yow.jobapp.review.ReviewRepository;
import com.yow.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(long companyId) {
        List<Review> reviews = reviewRepository.findReviewByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review getReviewById(long companyId, long reviewId){
        List<Review> reviews = reviewRepository.findReviewByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId() == reviewId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Review addReview(long companyId, Review review) {
        Company company = companyService.findCompanyById(companyId);
        if (company != null){
            review.setCompany(company);
            Review newReview = reviewRepository.save(review);
            return newReview;
        }
        return null;
    }

    @Override
    public Review updateReview(long companyId, long reviewId, Review review) {
        Company company = companyService.findCompanyById(companyId);
        if (company != null){
            Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
            if (reviewOptional.isPresent()){
                Review updatedReview = reviewOptional.get();
                updatedReview.setTitle(review.getTitle());
                updatedReview.setDescription(review.getDescription());
                updatedReview.setRating(review.getRating());
                updatedReview.setCompany(company);
                reviewRepository.save(updatedReview);
                return updatedReview;
            }
        }
        return null;
    }

    @Override
    public Boolean deleteReview(long companyId, long reviewId) {
        Company company = companyService.findCompanyById(companyId);
        if (company != null) {
            try {
                reviewRepository.deleteById(reviewId);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
