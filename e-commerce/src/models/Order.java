package models;

import adapters.Proxy;
import services.UsersService;

import java.util.ArrayList;
import java.util.Date;

public class Order extends Model {
    private Proxy<User> UserId;
    private ArrayList<OrderLine> orderLines = new ArrayList<>();
    private Date date;
    private Double expressDelivery;


    public void setUserId(String id) {
        UserId = new Proxy<>(id);
    }

    public User getUser() {
        return UserId.getObject(UsersService::getUserById);
    }

    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public double getTotalCost() {
        double totalCost = 0;
        for (OrderLine orderLine : orderLines) {
            totalCost += orderLine.getTotalPrice();
        }
        if (isExpressDelivery())
            totalCost += expressDelivery;
        return totalCost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public boolean isExpressDelivery() {
        return expressDelivery != null;
    }

    public void setExpressDelivery(Double expressDelivery) {
        if(expressDelivery!=null && expressDelivery > 0)
            this.expressDelivery = expressDelivery;
        else this.expressDelivery = null;
    }

}

