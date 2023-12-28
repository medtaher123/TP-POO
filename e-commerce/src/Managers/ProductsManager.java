package Managers;

import models.Product;
import models.products.*;
import services.ProductsService;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ProductsManager {
    public static final HashMap<String, Class<? extends Product>>  categoriesClassesMap = initMap();
    public static final String LAPTOPS_CATEGORY = "laptops";
    public static final String HOME_DECO_CATEGORY = "home-decoration";
    public static final String SMARTPHONES_CATEGORY = "smartphones";
    public static final String SKINCARE_CATEGORY = "skincare";
    public static final String FRAGRANCES_CATEGORY = "fragrances";
    public static final String GROCERIES_CATEGORY = "groceries";


    private static HashMap<String, Class<? extends Product>>  initMap(){
        HashMap<String, Class<? extends Product>>  map = new HashMap<>();
        map.put(LAPTOPS_CATEGORY, Laptop.class);
        map.put(HOME_DECO_CATEGORY, HomeDecoration .class);
        map.put(SMARTPHONES_CATEGORY, Smartphone .class);
        map.put(SKINCARE_CATEGORY, Skincare .class);
        map.put(FRAGRANCES_CATEGORY, Fragrance.class);
        map.put(GROCERIES_CATEGORY, Groceries.class);
        return map;
    }

    public static Class<? extends Product> getCategoriesClass(String category){
        return categoriesClassesMap.get(category);
    }

    public static String getCategoryFromClass(Class<? extends Product> productClass) {
        for (Map.Entry<String, Class<? extends Product>> entry : categoriesClassesMap.entrySet()) {
            if (entry.getValue().equals(productClass)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static Type getCategoriesClass(Product product) {
        return getCategoriesClass(product.getCategory());
    }

    public static void addStock(String productId, int quantity) { //TODO doc: this method gets productId instead of product to get the last version of product from the server to avoid concurrency issues.
        Product product = ProductsService.getProductById(productId);
        product.setStock(product.getStock()+quantity);
        ProductsService.updateProduct(product);
    }

    public static void removeStock(String productId, int quantity) {
        Product product = ProductsService.getProductById(productId);
        product.setStock(product.getStock()-quantity);
        ProductsService.updateProduct(product);
    }

    public static void deleteAllSerialNumbers(String id) {
        //not implemented
    }
}
