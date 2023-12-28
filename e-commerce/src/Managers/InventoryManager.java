package Managers;

import helpers.ConsoleHelper;
import models.Order;
import models.OrderLine;
import models.Product;
import models.SerialNumber;
import services.SerialNumbersService;
import services.ProductsService;

import java.util.Date;

public class InventoryManager {

    public static void addStock(Product product, int quantity) {
        ProductsManager.addStock(product.getId(),quantity);
        if (product.getSupportsSerialNumbers()) {
            generateSerialNumbers(product.getId(),quantity);
        }
    }

    private static void generateSerialNumbers(String productId, int quantity) {
        for (int i = 0; i < quantity; i++) {
            SerialNumber sn = new SerialNumber(productId);
            sn.setArrivalDate(new Date());
            sn = SerialNumbersService.addSerialNumber(sn);
            System.out.println("new SerialNumber created: " + sn.getShortDisplay());
            ConsoleHelper.sleep(200);//for the 3in //else the server crashes (too many requests)
        }
    }

    //I used this method to generate serial numbers for all products in the database (initialization)
    public static void main0(String[] args){
        Product[] products = ProductsService.getAllProducts();
        for (Product product : products) {
            if (product.getSupportsSerialNumbers()) {

                generateSerialNumbers(product.getId(),product.getStock());
            }
        }
    }

    public static void updateInventory(Order order) {
        for(OrderLine orderLine:order.getOrderLines()){
            ProductsManager.removeStock(orderLine.getProductId(),orderLine.getQuantity());
            if(orderLine.getSerialNumbers()!=null){
                for (String serialNumberId : orderLine.getSerialNumbers()) {
                    SerialNumbersService.setSerialNumberSold(serialNumberId,new Date());
                }
            }

        }
    }

}
