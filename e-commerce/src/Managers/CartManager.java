package Managers;

import models.Product;

import java.util.HashMap;
import java.util.Map;

public class CartManager {
    private final static HashMap<Product, Integer> cart = new HashMap<>();

    public static void addProduct(Product product) {
        addProduct(product, 1);
    }

    public static void addProduct(Product product, int quantity) {
        if (cart.containsKey(product)) {
            int currentQuantity = cart.get(product);
            cart.put(product, currentQuantity + quantity);
        } else {
            cart.put(product, quantity);
        }
    }
    public static void removeQuantity(Product product, int quantityToRemove) {
        if (cart.containsKey(product)) {
            int currentQuantity = cart.get(product);
            int newQuantity = currentQuantity - quantityToRemove;

            if (newQuantity <= 0) {
                cart.remove(product);
            } else {
                cart.put(product, newQuantity);
            }
        }
    }

    public static void removeProduct(Product product) {
        cart.remove(product);
    }

    public static void updateQuantity(Product product, int newQuantity) {
        if (cart.containsKey(product)) {
            cart.put(product, newQuantity);
        }
    }

    public static void clearCart() {
        cart.clear();
    }

    public static Map<Product, Integer> getCartContents() {
        return cart;
    }

    public static int getTotalItems() {
        int totalItems = 0;
        for (Integer quantity : cart.values()) {
            totalItems += quantity;
        }
        return totalItems;
    }

    public static double getTotalCost() {
        double totalCost = 0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalCost += product.getPrice() * quantity;
        }
        return totalCost;
    }

    // Other methods as per specific requirements of your e-commerce application
}
