package models.products;

import Managers.ProductsManager;
import models.Product;

public class Smartphone extends Product {

    private String os;
    private String screenSize;
    private String cameraResolution;

    public Smartphone() {
        setCategory(getCategory());
    }

    @Override
    protected String getCategorySpecificDetails() {
        return (os != null ? "Operating System: " + os + "\n" : "") +
                (screenSize != null ? "Screen Size: " + screenSize + "\n" : "") +
                (cameraResolution != null ? "Camera Resolution: " + cameraResolution + "\n" : "");
    }


    @Override
    public String getCategory() {
        return ProductsManager.SMARTPHONES_CATEGORY;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getCameraResolution() {
        return cameraResolution;
    }

    public void setCameraResolution(String cameraResolution) {
        this.cameraResolution = cameraResolution;
    }
}
