package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    default Review createReview(Review review) {
        return save(review);
    }

    default Review getReview(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteReview(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateReview(Review review) {
        if (existsById(review.getId())) {
            save(review);
            return true;
        }
        return false;
    }

    @Query("SELECT r FROM Review r")
    List<Review> getAllReviews();

    @Query("SELECT r FROM Review r JOIN FETCH r.user WHERE r.restaurant.id = ?1")
    List<Review> getReviewsByRestaurantId(int restaurantId);

    @Query("SELECT r FROM Review r WHERE r.user.id = ?1")
    List<Review> getReviewsByUser(int userId);

    @Query("SELECT (SUM(r.rating) / COUNT(r)) AS resultado FROM Review r WHERE r.restaurant.id = ?1")
    double getReviewRating(int idDish);

}
