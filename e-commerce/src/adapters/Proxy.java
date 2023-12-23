package adapters;

public class Proxy<T> {

    String id;
    T obj;
    boolean isLoaded;

    public Proxy(String id) {
        this.id = id;
    }

    public T getObject() {
        if(!isLoaded)
            retrieve();
        return obj;
    }

    private void retrieve() {
        obj = null; //TODO: call service
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


}
