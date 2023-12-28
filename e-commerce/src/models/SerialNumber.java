package models;

import adapters.Proxy;
import services.ProductsService;

import java.util.Date;

public class SerialNumber extends Model {
    public Proxy<Product> productId;
    private Date arrivalDate;
    private Date soldDate;
    private boolean sold; //redundant but useful for filtering. (if soldDate!=null then sold=true). The json-server does not support filtering by null values

    public SerialNumber(String productId) {
        this.productId = new Proxy<>(productId);
    }

    public void setProductId(String productId) {
        this.productId.setId(productId);
    }
    public Product getProduct(){
        return productId.getObject(ProductsService::getProductById);
    }
    public Date getArrivalDate() {
        return arrivalDate;
    }
    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
        this.sold = soldDate != null;
    }

    public boolean isSold() {
        return sold;
    }

}
