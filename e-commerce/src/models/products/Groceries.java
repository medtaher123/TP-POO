package models.products;

import Managers.ProductsManager;
import models.Product;

public class Groceries extends Product {
    private Boolean organic;
    private String quantity;
    private String nutritionFacts;

    public Groceries() {
        setCategory(getCategory());
    }

    @Override
    protected String getCategorySpecificDetails() {
        return (quantity != null ? "Quantity: " + quantity + "\n" : "") +
                (organic != null ? (organic ? "Organic" : "Not organic") + "\n" : "") +
                (nutritionFacts != null ? "Nutrition Facts: " + nutritionFacts + "\n" : "");
    }


    @Override
    public String getCategory() {
        return ProductsManager.GROCERIES_CATEGORY;
    }

    public Boolean getOrganic() {
        return organic;
    }

    public void setOrganic(Boolean organic) {
        this.organic = organic;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNutritionFacts() {
        return nutritionFacts;
    }

    public void setNutritionFacts(String nutritionFacts) {
        this.nutritionFacts = nutritionFacts;
    }
}
