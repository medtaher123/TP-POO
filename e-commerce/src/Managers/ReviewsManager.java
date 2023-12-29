package Managers;

import models.Product;
import models.UserReview;
import services.ReviewsService;

public class ReviewsManager {

    public static double getRating(Product product) {
        UserReview[] reviews = ReviewsService.getReviewsByProductId(product.getId());
        if (reviews.length == 0)
            return -1;
        double sum = 0;
        for (UserReview review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.length;
    }
}
