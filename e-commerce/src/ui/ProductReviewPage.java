package ui;

import authentication.AccessLevel;
import authentication.AuthenticationSystem;
import helpers.Action;
import helpers.ActionMenu;
import helpers.ConsoleHelper;
import helpers.DateHelper;
import models.Product;
import models.UserReview;
import services.ReviewsService;

import java.util.Date;

public class ProductReviewPage extends Page {

    private final Product product;
    private UserReview previousReview;

    public ProductReviewPage(Product product) {
        this.product = product;
    }

    @Override
    protected String getTitle() {
        return "Review for " + product.getTitle();
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.CUSTOMER;
    }

    @Override
    protected void printContent() {
        previousReview = ReviewsService.getReviewByUserIdAndProductId(AuthenticationSystem.getActiveUser().getId(), product.getId());

        if (previousReview != null) {
            System.out.println("You have already reviewed this product!");
            System.out.println("Your previous review:");
            System.out.println("Rating: " + previousReview.getRating());
            System.out.println("Review: " + previousReview.getReview());
            System.out.println("Date: " + DateHelper.format(previousReview.getDate()));
            ConsoleHelper.printNewLines(2);
            new ActionMenu(actions, ActionMenu.PREV_PAGE_ACTION, "").execute();
        } else {
            updateReview();
        }

    }

    private void updateReview() {
        UserReview review = previousReview;
        if (review == null) {
            review = new UserReview(AuthenticationSystem.getActiveUser().getId(), product.getId());
        }
        review.setRating(ConsoleHelper.readIntInRange("Rating (1-5): ", 1, 5));
        review.setReview(ConsoleHelper.input("Review"));
        review.setDate(new Date());
        if (previousReview != null) {
            ReviewsService.UpdateReview(review);
            ConsoleHelper.printSuccess("Review updated successfully. Thank you for your feedback!");
        } else {
            ReviewsService.addReview(review);
            ConsoleHelper.printSuccess("Review added successfully. Thank you for your feedback!");
        }
        new ActionMenu(null, ActionMenu.PREV_PAGE_ACTION, "").execute();
    }

    private final Action[] actions = {
            new Action() {
                @Override
                public String getDescription() {
                    return "Update review";
                }

                @Override
                public Object execute() {
                    updateReview();
                    return null;
                }
            },
            new Action() {
                @Override
                public String getDescription() {
                    return "Delete review";
                }

                @Override
                public Object execute() {
                    ReviewsService.DeleteReviewById(previousReview.getId());
                    ConsoleHelper.printSuccess("Review deleted successfully.");
                    new ActionMenu(null, ActionMenu.PREV_PAGE_ACTION, "").execute();
                    return null;
                }
            }
    };
}