package services;

import adapters.GsonInstance;
import models.Product;

public class ProductsService extends DatabaseService{
    public static Product[] getAllProducts() {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.PRODUCTS_API_URL), Product[].class);
    }

    public static Product[] getAllProducts(String queryParams) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.PRODUCTS_API_URL + "?" + queryParams),
                Product[].class);
    }

    public static Product getProductById(String id) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("GET", DatabaseService.PRODUCTS_API_URL + "/" + id), Product.class);
    }

    public static Product updateProduct(Product Product) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("PUT", DatabaseService.PRODUCTS_API_URL + "/" + Product.getId(),Product), Product.class);
    }
    public static Product addProduct(Product newProduct) {
        return GsonInstance.gson.fromJson(DatabaseService.sendHttpRequest("POST", DatabaseService.PRODUCTS_API_URL, newProduct), Product.class);/*gson.toJson(newProduct))*/
    }

    public static boolean DeleteProductById(String id) {
        DatabaseService.sendHttpRequest("DELETE", DatabaseService.PRODUCTS_API_URL + "/" + id);
        return true;
    }


    public static Product[] getAllProductsInPriceRange(double minPrice, double maxPrice, String queryParams) {
        return filter(getAllProducts(queryParams),product -> ((minPrice<=0 || product.getPrice()>=minPrice) && (maxPrice<=0 || product.getPrice()<=maxPrice)));
    }


}
