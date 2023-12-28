package models;

import adapters.Proxy;
import services.ProductsService;
import services.SerialNumbersService;

public class OrderLine extends Model{

    Proxy<Product> productId;
    int quantity;
    double unitaryPrice;

    private String[] serialNumbers;


    public OrderLine(Product product, int quantity,double unitaryPrice) {
        this.productId = new Proxy<>(product.getId());
        this.quantity = quantity;
        this.unitaryPrice = unitaryPrice;
        if(product.getSupportsSerialNumbers()){
            setSerialNumbers(fetchSerialNumbers(product.getId(),quantity));
        }
    }

    private SerialNumber[] fetchSerialNumbers(String productId, int quantity) {
        return SerialNumbersService.GetNumberOfAvailableInstancesOfProduct(productId,quantity);
    }


    public Product getProduct() {
        return productId.getObject(ProductsService::getProductById);
    }

    public String getProductId() {
        return productId.getId();
    }

    public double getTotalPrice() {
        return unitaryPrice * quantity;
    }

    public String[] getSerialNumbers() {
        return serialNumbers;
    }

    public void setSerialNumbers(SerialNumber[] serialNumbers) {
        this.serialNumbers = new String[serialNumbers.length];
        for (int i = 0; i < serialNumbers.length; i++) {
            this.serialNumbers[i] = serialNumbers[i].getId();
        }
    }

    public int getQuantity() {
        return quantity;
    }


}
