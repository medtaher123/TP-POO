package Managers;

import exceptions.ProductOutOfStockException;
import helpers.ConsoleColors;
import helpers.DataFormatter;
import helpers.TableLayout;
import models.OrderLine;
import models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartManager {
    private final static HashMap<Product, Integer> cart = new HashMap<>();
    private static boolean expressDelivery = false;

    public static void addProduct(Product product) throws ProductOutOfStockException {
        addProduct(product, 1);
    }

    public static void addProduct(Product product, int quantity) throws ProductOutOfStockException {
        if(product.getStock()<=0){
            throw new ProductOutOfStockException("Product " + product.getTitle() + " is out of stock.");
        }
        int currentQuantity = 0;
        if (cart.containsKey(product)) {
            currentQuantity = cart.get(product);
        }
        int newQuantity = currentQuantity + quantity;
        if(newQuantity > product.getStock()) {
            throw new ProductOutOfStockException("Only " + product.getStock() + " of product " + product.getTitle() + " are left in stock. Cannot add more.\n");
        }
        cart.put(product, newQuantity);
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

    public static Map<Product, Integer> getCart() {
        return cart;
    }

    public static int getTotalItems() {
        int totalItems = 0;
        for (Integer quantity : cart.values()) {
            totalItems += quantity;
        }
        return totalItems;
    }

    public static boolean containsProduct(Product product) {
        return cart.containsKey(product);
    }
    public static int getQuantity(Product product) {
        if(containsProduct(product))
            return cart.get(product);
        return 0;
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

    public static boolean cartIsEmpty() {
        return cart.isEmpty();
    }

    public static boolean isExpressDelivery() {
        return expressDelivery;
    }

    public static void setExpressDelivery(boolean expressDelivery) {
        CartManager.expressDelivery = expressDelivery;
    }

    public static ArrayList<OrderLine> getOrderLines() {
        ArrayList<OrderLine> orderLines = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            orderLines.add(new OrderLine(product, quantity, product.getPrice()));
        }
        return orderLines;
    }

    public static TableLayout createCartTableLayout() { //chose to return TableLayout instead of displaying it directly for more flexibility (can be modified before display)
        TableLayout table = new TableLayout();
        table.setHeaders("Index","Product","Quantity","Price","Total");
        table.setLeftPad(2,true);
        table.setLeftPad(3,true);
        table.setLeftPad(4,true);
        int i=0;
        for(Map.Entry<Product,Integer> entry : CartManager.getCart().entrySet()){
            Product p = entry.getKey();
            int q = entry.getValue();
            table.addRow(++i+"",p.getShortDisplay(),q+"", DataFormatter.formatPrice(p.getPrice(),false),DataFormatter.formatPrice(p.getPrice()*q));
        }
        table.addRowSeparator();

        double cartCost = CartManager.getTotalCost();
        double shippingCost = cartCost> SettingsManager.getSettings().getStartingCostForFreeDelivery() ?0:SettingsManager.getSettings().getDeliveryFees();
        double expressDeliveryFees = CartManager.isExpressDelivery()?SettingsManager.getSettings().getExpressDeliveryFees():0;

        table.addRow("","Shipping cost (free for purchases over " + DataFormatter.formatPrice(SettingsManager.getSettings().getStartingCostForFreeDelivery()) + ")","","",DataFormatter.formatPrice(shippingCost) );
        if(CartManager.isExpressDelivery())
            table.addRow("","Express delivery","","",DataFormatter.formatPrice(expressDeliveryFees));

        table.addRowSeparator();
        table.addRow("","","", ConsoleColors.getColoredString("Total:",ConsoleColors.DEFAULT_BOLD),DataFormatter.formatPrice(cartCost+shippingCost + expressDeliveryFees));

        return table;
    }
    public static void printCartTableLayout() {
        createCartTableLayout().displayTable();
    }
}
