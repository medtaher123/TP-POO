package models;

import adapters.Proxy;

import java.util.Date;

public class Coupon extends Model{
    public Proxy<Object> prr;
    String couponCode="21";
    String category;
    String Status;//TODO: make this enum?
    Date generatedTime;



}
