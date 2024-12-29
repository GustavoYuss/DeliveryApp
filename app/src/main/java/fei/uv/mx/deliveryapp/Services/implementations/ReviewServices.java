package fei.uv.mx.deliveryapp.Services.implementations;

import fei.uv.mx.deliveryapp.Models.Review;
import fei.uv.mx.deliveryapp.Repositories.ReviewRepository;
import fei.uv.mx.deliveryapp.Services.interfaces.IReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServices implements IReviewServices {
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review review) {
        return reviewRepository.createReview(review);
    }

    @Override
    public Review getReview(int id) {
        return reviewRepository.getReview(id);
    }

    @Override
    public boolean deleteReview(int id) {
        return reviewRepository.deleteReview(id);
    }

    @Override
    public boolean updateReview(Review review) {
        return reviewRepository.updateReview(review);
    }

    @Override
    public List<Review> getReviewsByDishId(int dishId) {
        return reviewRepository.getReviewsByDish(dishId);
    }

    @Override
    public double getReviewRating(int dishId) {
        return reviewRepository.getReviewRating(dishId);
    }

    @Override
    public List<Review> getReviewsByUserId(int userId) {
        return reviewRepository.getReviewsByUser(userId);
    }

}
