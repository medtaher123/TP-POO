package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonInstance {
    //TODO: add to doc: this class holds the configured gson object to use across the application
    public static final Gson gson = initGson();
    private static Gson initGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(new RecursiveTypeAdapterFactory());
        //gsonBuilder.setDateFormat("yyyy-MM-dd-HH-mm-ss");
        return gsonBuilder.create();
    }
}
