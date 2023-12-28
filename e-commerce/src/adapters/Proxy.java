package adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Proxy<T> {

    String id;
    T obj;
    boolean isLoaded;

    public Proxy(String id) {
        this.id = id;
    }


    public static <T> List<T> unwrap(List<Proxy<T>> list, Function<String,T>  retrieveMethod) {
        List<T> unwrappedList = new ArrayList<>();
        for (Proxy<T> proxy : list) {
            unwrappedList.add(proxy.getObject(retrieveMethod));
        }
        return unwrappedList;
    }


    public T getObject(Function<String,T>  retrieveMethod) {
        if(!isLoaded)
            retrieve(retrieveMethod);
        return obj;
    }

    private void retrieve(Function<String, T> retrieveMethod) {
        obj = retrieveMethod.apply(id);
        isLoaded = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(this.id.equals(id))
            return;
        this.id = id;
        isLoaded=false;
        obj=null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proxy<?> proxy = (Proxy<?>) o;
        return Objects.equals(id, proxy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
