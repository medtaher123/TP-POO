package Managers;

import models.Product;
import models.products.*;

import java.lang.reflect.Type;
import java.util.HashMap;

public class ProductsManager {
    public static final HashMap<String, Class<? extends Product>>  categoriesClassesMap = initMap();


    private static HashMap<String, Class<? extends Product>>  initMap(){
        HashMap<String, Class<? extends Product>>  map = new HashMap<>();
        map.put("laptops", Laptop.class);
        map.put("home-decoration", HomeDecoration .class);
        map.put("smartphones", Smartphone .class);
        map.put("skincare", Skincare .class);
        map.put("fragrances", Fragrances .class);
        map.put("groceries", Groceries.class);
        return map;
    }

    public static Class<? extends Product> getCategoriesClass(String category){
        return categoriesClassesMap.get(category);
    }

    public static Type getCategoriesClass(Product product) {
        return getCategoriesClass(product.getCategory());
    }
}
