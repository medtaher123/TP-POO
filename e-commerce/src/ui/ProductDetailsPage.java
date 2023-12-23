package ui;

import authentication.AccessLevel;
import models.Product;

public class ProductDetailsPage extends  Page {
    Product product;
    public ProductDetailsPage(Product product) {
        this.product=product;
    }

    @Override
    protected String getTitle() {
        return product.getShortDisplay();
    }

    @Override
    public int getAccessLevel() {
        return AccessLevel.ALL_EXCEPT_GUEST;
    }

    @Override
    protected void printContent() {
        System.out.println(product.getLongDisplay());
    }
}
