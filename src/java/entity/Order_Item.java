package entity;

import java.math.BigDecimal;

public class Order_Item {

    private order orderId;
    private int idorder;
    private int itemId;
    private Product product;
    private int quantity;

    private double listPrice;
    private double discount;

    public int getIdorder() {
        return idorder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }

    public Order_Item(int idorder, int itemId, Product product, int quantity, double listPrice, double discount) {
        this.idorder = idorder;
        this.itemId = itemId;
        this.product = product;
        this.quantity = quantity;
        this.listPrice = listPrice;
        this.discount = discount;
    }

    public Order_Item(order orderId, int itemId, Product product, int quantity, double listPrice, double discount) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.product = product;
        this.quantity = quantity;
        this.listPrice = listPrice;
        this.discount = discount;
    }

    public order getOrderId() {
        return orderId;
    }

    public void setOrderId(order orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Order_Item{" + "orderId=" + orderId + ", itemId=" + itemId + ", product=" + product + ", quantity=" + quantity + ", listPrice=" + listPrice + ", discount=" + discount + '}';
    }

}
