package models;

import adapters.Proxy;
import services.ProductsService;
import services.UsersService;

import java.util.Date;

public class UserReview extends Model{
    Proxy<User> userId;
    Proxy<Product> productId;
    int rating;
    String review;
    Date date;

    public UserReview(String userId, String productId){
        this.userId = new Proxy<>(userId);
        this.productId = new Proxy<>(productId);
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public User getUser(){
        return userId.getObject(UsersService::getUserById);
    }
    public Product getProduct(){
        return productId.getObject(ProductsService::getProductById);
    }
    public void setUser(User user){
        userId = new Proxy<>(user.getId());
    }
    public void setProduct(Product product){
        productId = new Proxy<>(product.getId());
    }
}
