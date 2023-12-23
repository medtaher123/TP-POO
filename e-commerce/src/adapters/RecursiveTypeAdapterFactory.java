package adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import models.Product;

public class RecursiveTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (rawType == Proxy.class) {
            return (TypeAdapter<T>) new ProxyTypeAdapter();
        }
        if(rawType == Product.class){//Product.class.isAssignableFrom(rawType)){
            return (TypeAdapter<T>) new ProductAdapter(gson);
        }

        return null;
    }
}
