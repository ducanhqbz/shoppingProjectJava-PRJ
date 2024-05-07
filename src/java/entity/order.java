package entity;

import java.util.Date;

public class order {
    private int orderId;
    private Customer customer;
    private int orderStatus;
    private String orderDate;
    private String requiredDate;
    private String shippedDate;
    private Store store;
    private Staff staff;

    public order(int orderId, Customer customerId, int orderStatus, String orderDate, String requiredDate, String shippedDate, Store storeId, Staff staffId) {
        this.orderId = orderId;
        this.customer = customerId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.store = storeId;
        this.staff= staffId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customerId) {
        this.customer = customerId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(String shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store storeId) {
        this.store = storeId;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staffId) {
        this.staff = staffId;
    }

    @Override
    public String toString() {
        return "order{" + "orderId=" + orderId + ", customerId=" + customer + ", orderStatus=" + orderStatus + ", orderDate=" + orderDate + ", requiredDate=" + requiredDate + ", shippedDate=" + shippedDate + ", storeId=" + store + ", staffId=" + staff + '}';
    }


  
}
