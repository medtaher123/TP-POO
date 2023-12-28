package Managers;

import authentication.AuthenticationSystem;
import models.Order;
import services.OrdersService;

import java.util.Date;

public class OrderManager {


    public static void createOrder() {
        Order order = buildOrderFromCart();
        saveOrder(order);
        CartManager.clearCart();
    }

    private static void saveOrder(Order order) {
        OrdersService.addOrder(order);
        InventoryManager.updateInventory(order);
    }

    public static Order buildOrderFromCart() {
        Order order = new Order();
        order.setUserId(AuthenticationSystem.getActiveUser().getId());
        order.setOrderLines(CartManager.getOrderLines());
        order.setDate(new Date());
        order.setExpressDelivery(CartManager.isExpressDelivery() ? SettingsManager.getSettings().getExpressDeliveryFees() : null);
        return order;
    }

}
