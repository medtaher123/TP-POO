package services;

import adapters.GsonInstance;
import helpers.QueryParamsBuilder;
import models.SerialNumber;

import java.util.Date;

public class SerialNumbersService extends DatabaseService{
    public static SerialNumber[] getAllSerialNumbers() {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.SERIAL_NUMBERS_API_URL), SerialNumber[].class);
    }

    public static SerialNumber[] getAllSerialNumbers(String queryParams) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.SERIAL_NUMBERS_API_URL + "?" + queryParams),
                SerialNumber[].class);
    }
    public static SerialNumber[] getAllSerialNumbersOfProduct(String productId){
        QueryParamsBuilder params = new QueryParamsBuilder();
        params.addQueryParam("productId",productId);
        return getAllSerialNumbers(params.toString());
    }
    public static SerialNumber[] GetAllAvailableSerialNumbersOfProduct(String productId){
        QueryParamsBuilder params = new QueryParamsBuilder();
        params.addQueryParam("productId",productId);
        params.addQueryParam("sold","false");
        //params.addQueryParam();
        return getAllSerialNumbers(params.toString());
    }
    public static SerialNumber getProductById(String id) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.SERIAL_NUMBERS_API_URL + "/" + id), SerialNumber.class);
    }

    public static SerialNumber UpdateProduct(SerialNumber Product) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("PUT", DatabaseService.SERIAL_NUMBERS_API_URL + "/" + Product.getId(),Product), SerialNumber.class);
    }
    public static SerialNumber addSerialNumber(SerialNumber newSerialNumber) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("POST", DatabaseService.SERIAL_NUMBERS_API_URL, newSerialNumber), SerialNumber.class);
    }

    public static boolean DeleteSerialNumberById(String id) {
        DatabaseService.sendHttpRequest("DELETE", DatabaseService.SERIAL_NUMBERS_API_URL + "/" + id);
        return true;
    }


    public static void setSerialNumberSold(String serialNumberId, Date date) {
        SerialNumber sn = getProductById(serialNumberId);
        sn.setSoldDate(date);
        UpdateProduct(sn);
    }
    public static SerialNumber[] GetNumberOfAvailableInstancesOfProduct(String productId, int quantity) {
        QueryParamsBuilder params = new QueryParamsBuilder();
        params.addQueryParam("productId",productId);
        params.addQueryParam("sold","false");
        params.addLimitParam(quantity);

        SerialNumber[] instances = getAllSerialNumbers(params.toString());
        if (instances.length >= quantity) {
            return instances;
        }
        else {
            throw new IllegalArgumentException("There are not enough instances of this product");
        }
    }
}
