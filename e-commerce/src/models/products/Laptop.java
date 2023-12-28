package models.products;

import Managers.ProductsManager;
import models.Product;

public class Laptop extends Product {
    private String processor;
    private String ram; //TODO: add to doc: chose Integer over int to support null value (in the database, nothing is stored instead of 0)
    private String storage;
    private String displaySize;
    private String operatingSystem;

    public Laptop() {
        setCategory(getCategory());
    }

    @Override
    protected String getCategorySpecificDetails() {
        return (processor != null ? "Processor: " + processor + "\n" : "") +
                (ram != null ? "RAM: " + ram + "\n" : "") +
                (storage != null ? "Storage: " + storage + "\n" : "") +
                (displaySize != null ? "Display Size: " + displaySize + "\n" : "") +
                (operatingSystem != null ? "Operating System: " + operatingSystem + "\n" : "");
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCategory(){
        return ProductsManager.LAPTOPS_CATEGORY;
    }
    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
}
