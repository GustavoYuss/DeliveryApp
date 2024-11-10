package fei.uv.mx.deliveryapp.Services.interfaces;

import fei.uv.mx.deliveryapp.Models.Review;
import java.util.List;

public interface IReviewServices {
    Review createReview(Review review);
    Review getReview(int id);
    boolean deleteReview(int id);
    boolean updateReview(Review review);
    List<Review> getReviewsByDishId(int dishId);
    List<Review> getReviewsByUserId(int userId);
}
