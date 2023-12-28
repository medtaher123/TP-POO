package services;

import adapters.GsonInstance;
import helpers.QueryParamsBuilder;
import models.UserReview;

public class ReviewsService extends DatabaseService{
    
    public static UserReview[] getAllReviews() {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.REVIEWS_API_URL), UserReview[].class);
    }

    public static UserReview[] getAllReviews(String queryParams) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.REVIEWS_API_URL + "?" + queryParams),
                UserReview[].class);
    }

    public static UserReview getReviewById(String id) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.REVIEWS_API_URL + "/" + id), UserReview.class);
    }

    public static UserReview[] getReviewsByUserId(String userId) {
        QueryParamsBuilder params = new QueryParamsBuilder();
        params.addQueryParam("userId", userId);
        return getAllReviews(params.toString());
    }

    public static UserReview[] getReviewsByProductId(String productId) {
        QueryParamsBuilder params = new QueryParamsBuilder();
        params.addQueryParam("productId", productId);
        return getAllReviews(params.toString());
    }

    public static UserReview UpdateReview(UserReview review) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("PUT", DatabaseService.REVIEWS_API_URL + "/" + review.getId(), review), UserReview.class);
    }

    public static UserReview addReview(UserReview newReview) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("POST", DatabaseService.REVIEWS_API_URL, newReview), UserReview.class);
    }

    public static boolean DeleteReviewById(String id) {
        DatabaseService.sendHttpRequest("DELETE", DatabaseService.REVIEWS_API_URL + "/" + id);
        return true;
    }


    public static UserReview getReviewByUserIdAndProductId(String userId, String productId) {
        QueryParamsBuilder params = new QueryParamsBuilder();
        params.addQueryParam("userId",  userId);
        params.addQueryParam("productId", productId);
        params.addLimitParam(1);
        UserReview[] reviews = getAllReviews(params.toString());
        if(reviews.length == 0)
            return null;
        return reviews[0];
    }
}
