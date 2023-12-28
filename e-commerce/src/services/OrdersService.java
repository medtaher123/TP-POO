package services;

import adapters.GsonInstance;
import helpers.QueryParamsBuilder;
import models.Order;

public class OrdersService extends DatabaseService {

    public static Order[] getAllOrders() {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.ORDERS_API_URL), Order[].class);
    }

    public static Order[] getAllOrders(String queryParams) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.ORDERS_API_URL + "?" + queryParams),
                Order[].class);
    }

    public static Order getOrderById(String id) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.ORDERS_API_URL + "/" + id), Order.class);
    }

    public static Order[] getOrdersByUserId(String userId) {
        QueryParamsBuilder params = new QueryParamsBuilder();
        params.addQueryParam("userId", userId);
        return getAllOrders(params.toString());
    }

    public static Order UpdateOrder(Order Order) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("PUT", DatabaseService.ORDERS_API_URL + "/" + Order.getId(), Order), Order.class);
    }

    public static Order addOrder(Order newOrder) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("POST", DatabaseService.ORDERS_API_URL, newOrder), Order.class);/*gson.toJson(newOrder))*/
    }

    public static boolean DeleteOrderById(String id) {
        DatabaseService.sendHttpRequest("DELETE", DatabaseService.ORDERS_API_URL + "/" + id);
        return true;
    }
}
