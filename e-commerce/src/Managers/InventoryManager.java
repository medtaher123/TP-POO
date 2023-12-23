package Managers;

import helpers.ConsoleHelper;
import models.Product;
import models.products.ProductInstance;
import services.InventoryService;
import services.ProductsService;

import java.util.Date;

public class InventoryManager {

    public static void addProductInstances(String productId, int quantity) {
        for (int i = 0; i < quantity; i++) {
            ProductInstance pi = new ProductInstance(productId);
            pi.setArrivalDate(new Date());
            pi = InventoryService.addProductInstance(pi);
            System.out.println(pi.getShortDisplay());
            ConsoleHelper.sleep(100);//for the 3in //else the server crashes (too many requests)
        }
    }

    public static void main(String[] args){
        Product[] products = ProductsService.getAllProducts();
        for (Product product : products) {
            addProductInstances(product.getId(),20);
        }


    }

}
