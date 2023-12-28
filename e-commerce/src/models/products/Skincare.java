package models.products;

import Managers.ProductsManager;
import models.Product;

public class Skincare extends Product {
    private String skinType;
    private String usage;
    private Boolean organic;
    private String ingredients;

    public Skincare() {
        setCategory(getCategory());
    }
    @Override
    public String getCategory() {
        return ProductsManager.SKINCARE_CATEGORY;
    }

    @Override
    protected String getCategorySpecificDetails() {
        return (skinType != null ? "Skin Type: " + skinType + "\n" : "") +
                (usage != null ? "Usage: " + usage + "\n" : "") +
                (organic != null ? (organic ? "Organic\n" : "Not organic\n") : "") +
                (ingredients != null ? "Ingredients: " + ingredients + "\n" : "");
    }

    public String getSkinType() {
        return skinType;
    }

    public void setSkinType(String skinType) {
        this.skinType = skinType;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Boolean getOrganic() {
        return organic;
    }

    public void setOrganic(Boolean organic) {
        this.organic = organic;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
