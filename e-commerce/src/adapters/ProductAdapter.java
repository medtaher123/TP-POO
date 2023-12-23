package adapters;

import Managers.ProductsManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import models.Product;

import java.io.IOException;

public class ProductAdapter extends TypeAdapter<Product> {

    private Gson gson;

    public ProductAdapter(Gson gson) { // pass the configured gson
        this.gson = gson;
    }

    @Override
    public void write(JsonWriter out, Product value) throws IOException {
        //throw new UnsupportedOperationException("Serialization not supported");
        new Gson().toJson(value,ProductsManager.getCategoriesClass(value),out);  //infinite loop when using the passed gson attribute (gson calls adapter and adapter calls gson)
        System.out.println("product type adapter write method called with category="+value.getCategory());
    }


    @Override
    public Product read(JsonReader in) throws IOException {
        //Gson gson = new Gson();
        JsonObject productObject = new Gson().fromJson(in, JsonObject.class);

        // Get the category of the product from the JSON
        String category = productObject.get("category").getAsString();


        // Check if the category exists in the categoriesClassesMap
        if (ProductsManager.categoriesClassesMap.containsKey(category)) {
            // Get the class corresponding to the category
            Class<? extends Product> productClass = ProductsManager.getCategoriesClass(category);

            // Parse the JSON into the corresponding class
            return gson.fromJson(productObject, productClass);
        } else {
            System.out.println("Unknown category: " + category); //TODO: to remove
            return new Gson().fromJson(productObject, Product.class);
        }
    }

}
