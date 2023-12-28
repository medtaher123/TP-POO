package models.products;

import Managers.ProductsManager;
import models.Product;

public class HomeDecoration extends Product {
    private String material;
    private String color;
    private String size;
    private String room;

    public HomeDecoration() {
        setCategory(getCategory());
    }

    @Override
    protected String getCategorySpecificDetails() {
        return (material != null ? "Material: " + material + "\n" : "") +
                (color != null ? "Color: " + color + "\n" : "") +
                (size != null ? "Size: " + size + "\n" : "") +
                (room != null ? "Room: " + room + "\n" : "");
    }


    @Override
    public String getCategory() {
        return ProductsManager.HOME_DECO_CATEGORY;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}