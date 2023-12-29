package models;

import Managers.ReviewsManager;
import helpers.ConsoleColors;
import helpers.ConsoleHelper;
import helpers.DataFormatter;

import java.util.HashMap;
import java.util.List;

public abstract class Product extends Model {

    private String title;
    private String description;
    private double price;
    private int stock;
    private String brand;
    private String category;
    private String thumbnail;
    private List<String> images;
    private HashMap<String, String> otherDetails; //TODO doc: key: detail name, value: detail value (json supports maps), any detail can be added to any product, regardless of category

    private boolean supportsSerialNumbers = false;

    @Override
    public String getShortDisplay() {
        return title + " _ " + brand;
    }

    @Override
    public String getLongDisplay() {
        double rating = getRating();
        return "Title: " + title + "\n" +
                ((description != null && !description.isEmpty()) ? "Description: " + description + "\n" : "")
                + (price > 0 ? "Price: " + DataFormatter.formatPrice(price) + "\n" : "")
                + (brand != null && !brand.isEmpty() ? "Brand: " + brand + "\n" : "")
                + (category != null && !category.isEmpty() ? "Category: " + category + "\n" : "")
                + getCategorySpecificDetails()
                + getOtherDetails()
                + (thumbnail != null && !thumbnail.isEmpty() ? "Thumbnail: " + thumbnail + "\n" : "")
                + (images != null && !images.isEmpty() ? "Images: " + String.join(", ", images) + "\n" : "")
                + ConsoleColors.getColoredString("Rating: " + (rating > 0 ? rating + "/5\n" : "no ratings\n"), ConsoleHelper.HINT_COLOR);
    }

    private String getOtherDetails() {
        StringBuilder sb = new StringBuilder();
        if(otherDetails != null && !otherDetails.isEmpty()){
            sb.append("Other details: \n");
            for (String key : otherDetails.keySet()) {
                sb.append(key).append(": ").append(otherDetails.get(key)).append("\n");
            }
        }
        return sb.toString();
    }


    protected abstract String getCategorySpecificDetails();

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

    public Boolean getSupportsSerialNumbers() {
        return supportsSerialNumbers;
    }

    public void setSupportsSerialNumbers(Boolean supportsSerialNumbers) {
        this.supportsSerialNumbers = supportsSerialNumbers;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setOtherDetails(String key, String value) {
        if(otherDetails == null){
            otherDetails = new HashMap<>();
        }
        otherDetails.put(key,value);
    }
    public double getRating() {
        return ReviewsManager.getRating(this);
    }
}
