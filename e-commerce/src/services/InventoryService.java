package services;

import adapters.GsonInstance;
import helpers.QueryParamsBuilder;
import models.Product;
import models.products.ProductInstance;

public class InventoryService extends DatabaseService{
    public static ProductInstance[] getAllProducts() {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.INVENTORY_API_URL), ProductInstance[].class);
    }

    public static ProductInstance[] getAllProducts(String queryParams) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.INVENTORY_API_URL + "?" + queryParams),
                ProductInstance[].class);
    }
    public static ProductInstance[] GetAllInstancesOfProduct(String productId){
        QueryParamsBuilder params = new QueryParamsBuilder();
        params.addQueryParam("productId",productId);
        return getAllProducts(params.toString());
    }
    public static ProductInstance[] GetAllAvailableInstancesOfProduct(String productId){ //TODO: to implement after choosing how to handle payments and purchases
        QueryParamsBuilder params = new QueryParamsBuilder();
        params.addQueryParam("productId",productId);
        //params.addQueryParam();
        return getAllProducts(params.toString());
    }

    public static ProductInstance getProductById(String id) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.INVENTORY_API_URL + "/" + id), ProductInstance.class);
    }

    public static ProductInstance UpdateProduct(Product Product) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("PUT", DatabaseService.INVENTORY_API_URL + "/" + Product.getId(),Product), ProductInstance.class);
    }
    public static ProductInstance addProductInstance(ProductInstance newProductInstance) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("POST", DatabaseService.INVENTORY_API_URL, newProductInstance), ProductInstance.class);
    }

    public static boolean DeleteProductById(String id) {
        DatabaseService.sendHttpRequest("DELETE", DatabaseService.INVENTORY_API_URL + "/" + id);
        return true;
    }


}
