package models.products;


import Managers.ProductsManager;
import models.Product;

public class Fragrance extends Product {

    private String gender;

    public Fragrance() {
        setCategory(getCategory());
    }
    @Override
    public String getCategory() {
        return ProductsManager.FRAGRANCES_CATEGORY;
    }

    @Override
    protected String getCategorySpecificDetails() {
        return (gender != null ? "Gender: " + gender + "\n" : "");
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
