package models;

import helpers.DataFormatter;

import java.util.List;

public class Product extends Model{

    private String title;
    private String description;
    private double price;
    //private double discountPercentage;
    //private double rating;
    //private int stock;
    private String brand;
    private String category;
    private String thumbnail;
    private List<String> images;

    @Override
    public String getShortDisplay(){
        return title + " _ " + brand;
    }

    @Override
    public String getLongDisplay(){
        return "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Price: " + DataFormatter.formatPrice(price) + "\n" +
                "Brand: " + brand + "\n" +
                "Category: " + category + "\n" +
                "Thumbnail: " + thumbnail + "\n" +
                "Images: " + images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
