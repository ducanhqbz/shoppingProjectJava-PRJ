/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
import entity.Order_Item;
import entity.Product;
import entity.Staff;
import entity.Store;
import entity.order;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class OrderItem_Dao extends DBConnect {

//    public void displayall() {
//        ArrayList<Order_Item> list = new ArrayList<>();
//        String sql = "select * from order_items";
//        try {
//            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
//            while (rs.next()) {
//                int orderId = rs.getInt("order_Id");
//                int itemId = rs.getInt("item_Id");
//                int productId = rs.getInt("product_id");
//                int quantity = rs.getInt("quantity");
//                double list_price = rs.getDouble("list_price");
//                double discount = rs.getDouble("discount");
//                list.add(new Order_Item(orderId, itemId, productId, quantity, list_price, discount));
////               list.add(new Staff(staff_id, first_name, last_name, email, phone, active, store_id, manager_id));
//            }
//        } catch (SQLException e) {
//
//        }
//        for (Order_Item order_Item : list) {
//            System.out.println(order_Item);
//        }
//    }
    public int removeOrderItem(int orderid, int itemID) {
        int n = 0;
        String sql = "DELETE FROM order_items WHERE order_id =?  and item_id = ? ";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, orderid);
            statement.setInt(2, itemID);
            n = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log the exception appropriately
        }
        return n;
    }

    public Product getProductWithID(int id) {
        String sql = "select * from products where product_id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int model_year = rs.getInt("model_year");
                double list_price = rs.getDouble("list_price");
                String brand_name = rs.getString("brand_name");
                String category_name = rs.getString("category_name");
                int status = rs.getInt("status");
                Product pro = new Product(product_id, product_name, model_year, list_price, brand_name, category_name, status);
                return pro;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public int addOrder_item(Order_Item obj) {
        int n = 0;
        String sql = "INSERT INTO [order_items] "
                + "([order_id], [item_id], [product_id], [quantity], [list_price], [discount]) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, obj.getOrderId().getOrderId());
            pre.setInt(2, obj.getItemId());
            pre.setInt(3, obj.getProduct().getProduct_id());
            pre.setInt(4, obj.getQuantity());
            pre.setDouble(5, obj.getListPrice());
            pre.setDouble(6, obj.getDiscount());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            // Xử lý ngoại lệ ở đây (ví dụ: ghi log hoặc báo cáo lỗi)
            ex.printStackTrace();
        }
        System.out.println(sql);
        return n;
    }

    public int addOrder_itemver2(Order_Item obj) {
        int n = 0;
        String sql = "INSERT INTO [order_items] "
                + "([order_id], [item_id], [product_id], [quantity], [list_price], [discount]) "
                + "VALUES ("+obj.getIdorder()+","+ obj.getItemId()+","+ obj.getProduct().getProduct_id()+","+obj.getQuantity()+","+ obj.getListPrice()+","+obj.getDiscount()+")";
        try {
         Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        

        n =state.executeUpdate(sql);
            System.out.println(sql); 
                   
        } catch (SQLException ex) {
            // Xử lý ngoại lệ ở đây (ví dụ: ghi log hoặc báo cáo lỗi)
            ex.printStackTrace();
        }
        System.out.println(sql);
        return n;
    }

    public ArrayList<Order_Item> getAll() {
        ArrayList<Order_Item> list = new ArrayList<>();
        String sql = "select * from Order_items";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderId = rs.getInt("order_Id");
                order order = getorderwithid(orderId);
                int itemId = rs.getInt("item_Id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Product pro = getProductWithID(productId);
                double list_price = rs.getDouble("list_price");
                double discount = rs.getDouble("discount");
                list.add(new Order_Item(order, itemId, pro, quantity, list_price, discount));
//               list.add(new Staff(staff_id, first_name, last_name, email, phone, active, store_id, manager_id));
            }
            return list;
        } catch (SQLException e) {

        }
        return null;

    }

    public ArrayList<Order_Item> getAllWithOrderID(int id) {
        ArrayList<Order_Item> list = new ArrayList<>();
        String sql = "select * from Order_items where order_id=" + id;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderId = rs.getInt("order_Id");
                order order = getorderwithid(orderId);
                int itemId = rs.getInt("item_Id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Product pro = getProductWithID(productId);
                double list_price = rs.getDouble("list_price");
                double discount = rs.getDouble("discount");
                list.add(new Order_Item(order, itemId, pro, quantity, list_price, discount));
//               list.add(new Staff(staff_id, first_name, last_name, email, phone, active, store_id, manager_id));
            }
            return list;
        } catch (SQLException e) {

        }
        return null;

    }

    public Store getStorewithid(int id) {
        Store store = null;
        String sql = "select * from Stores where store_id = " + id;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                int Store_id = rs.getInt("store_id");
                String store_name = rs.getString("store_name");

                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String stateStore = rs.getString("state");
                String zip_code = rs.getString("zip_code");
                store = new Store(Store_id, store_name, phone, email, street, city, street, zip_code);

            }
            return store;
        } catch (SQLException e) {

        }
        return null;
    }

    public Staff getStaffWithId(int id) {
        Staff staff = null;
        String sql = "SELECT * FROM staffs WHERE staff_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int staff_id = rs.getInt("staff_id");
                    String first_name = rs.getString("first_name");
                    String last_name = rs.getString("last_name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    int active = rs.getInt("active");
                    int store_id = rs.getInt("store_id");
                    Store store = getStorewithid(store_id);
                    int manager_id = rs.getInt("manager_id");
                    String password = rs.getString("password");
                    staff = new Staff(staff_id, first_name, last_name, email, phone, active, store, manager_id, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ một cách chính xác
        }
        return staff;
    }

    public Customer getCustomerWithId(int id) {
        Customer list = null;
        String sql = "select * from Customers where customer_id = " + id;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String stateCus = rs.getString("state");
                String zip_code = rs.getString("zip_code");
                String password = rs.getString("password");
                list = new Customer(customer_id, first_name, last_name, phone, email, street, city, stateCus, zip_code, password);
            }
            return list;
        } catch (SQLException e) {

        }
        return null;
    }

    public order getorderwithid(int id) {
        order list = null;
        String sql = "select * from Orders where order_id=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt("order_id");
                int customer_id = rs.getInt("customer_id");
                Customer cus = getCustomerWithId(customer_id);
                int orderStatus = rs.getInt("order_status");
                String orderDate = rs.getString("order_date");
                String requiredDate = rs.getString("required_date");
                String shippedDate = rs.getString("shipped_date");
                int store_id = rs.getInt("store_id");
                Store store = getStorewithid(orderId);
                int staff_id = rs.getInt("staff_id");
                Staff staff = getStaffWithId(staff_id);
                list = new order(orderId, cus, orderStatus, orderDate, requiredDate, shippedDate, store, staff);
            }
            return list;
        } catch (SQLException e) {
        }
        return null;

    }


    public static void main(String[] args) {
        // Create a Product instance
     OrderItem_Dao dao = new OrderItem_Dao();
Product product = dao.getProductWithID(1);
// Assuming 101 is a valid product ID

        // Create an Order_Item instance
        Order_Item orderItem = new Order_Item(1, 1, product, 5, 29.99, 0.05);

        // Assuming 'addOrder_itemver2' method is part of a class that requires database connection
        // This class should have the method 'addOrder_itemver2'

        // Add order item to the database
        int result = dao.addOrder_itemver2(orderItem);

        // Output the result
        if(result > 0) {
            System.out.println("Order item successfully added.");
        } else {
            System.out.println("Failed to add order item.");
        }
    }
}


