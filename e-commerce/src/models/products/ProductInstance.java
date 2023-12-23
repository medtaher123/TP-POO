package models.products;

import adapters.Proxy;
import models.Model;
import models.Product;

import java.util.Date;

public class ProductInstance extends Model {
    public Proxy<Product> productId;
    private Date arrivalDate;

    public ProductInstance(String productId) {
        this.productId = new Proxy<>(productId);
    }

    public void setProductId(String productId) {
        this.productId.setId(productId);
    }
    public Product getProduct(){
        return productId.getObject();
    }
    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

}
