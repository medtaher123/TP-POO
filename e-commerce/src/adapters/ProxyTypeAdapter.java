package adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import models.Coupon;

import java.io.IOException;

public class ProxyTypeAdapter extends TypeAdapter<Proxy> {

    @Override
    public void write(JsonWriter out, Proxy proxy) throws IOException {
        if (proxy != null) {
            out.value(proxy.getId());
        } else {
            out.nullValue();
        }
    }

    @Override
    public Proxy read(JsonReader in) throws IOException {
        return new Proxy(in.nextString());
    }

    public static void main0(String[] args) {
        // Create a GsonBuilder and register the custom TypeAdapter
        /*GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(Proxy.class, new ProxyTypeAdapter());
        gsonBuilder.registerTypeAdapterFactory(new RecursiveTypeAdapterFactory());
        // Create Gson object with the customized behavior
        Gson gson = gsonBuilder.create();*/

        // Usage example:
        // Serialize the Proxy object to JSON
        Proxy proxy = new Proxy("123"); // Assuming Proxy class has a constructor that accepts id
        Coupon c = new Coupon();
        c.prr = proxy;
        String json = GsonInstance.gson.toJson(c);
        System.out.println("Serialized JSON: " + json);

        Coupon p = GsonInstance.gson.fromJson("{\"prr\":\"123\"}",Coupon.class);


    }
}
