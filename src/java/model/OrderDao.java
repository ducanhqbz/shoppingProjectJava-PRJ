/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
import entity.order;
import entity.Staff;
import entity.Store;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class OrderDao extends DBConnect {

//    public int GetMaxorderId() {
//        String sql = "select max(order_id) as order_id from orders";
//        
//        return ;
//    }
    public order GetOrderWithId(int id) {
        order o = null;
        String sql = "select * from orders where order_id=" + id;
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
                Staff staff = getStaffWithid(staff_id);
                o = new order(orderId, cus, orderStatus, orderDate, requiredDate, shippedDate, store, staff);
            }
            return o;
        } catch (SQLException e) {
        }
        return null;

    }

    public int getNewOrderID() {

        String sql1 = "select max(order_id) as order_id from orders";
        int count = 0;

        try {
            PreparedStatement st = conn.prepareStatement(sql1);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt("order_id");
                count++;
            }
            return count;
        } catch (SQLException e) {

        }
        return count;
    }

public int addorder(order obj) {
    int n = 0;
    String sql = "INSERT INTO [dbo].[orders] ([order_id], [customer_id], [order_status], [order_date], [required_date], [shipped_date], [store_id], [staff_id]) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement pre = conn.prepareStatement(sql)) {
        pre.setInt(1, obj.getOrderId());
        pre.setInt(2, obj.getCustomer().getCustomerId());
        pre.setInt(3, obj.getOrderStatus());
        pre.setString(4, obj.getOrderDate());
        pre.setString(5, obj.getRequiredDate());
        pre.setString(6, obj.getShippedDate());
        pre.setInt(7, obj.getStore().getStoreId());
        pre.setInt(8, obj.getStaff().getStaff_id());

        n = pre.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();  // Improved error handling
    }
    return n;
}


//    public void displayAllOrdersWithDate(String date) {
//        ArrayList<order> list = new ArrayList<>();
//        String sql = "SELECT * FROM orders WHERE order_date = ?";
//        try {
//            PreparedStatement state = conn.prepareStatement(sql);
//            state.setString(1, date); // Corrected index
//            ResultSet rs = state.executeQuery();
//            while (rs.next()) {
//                int order_id = rs.getInt("order_id");
//                int customer_id = rs.getInt("customer_id");
//                int order_status = rs.getInt("order_status");
//                String order_date = rs.getString("order_date");
//                String required_date = rs.getString("required_date");
//                String shipped_date = rs.getString("shipped_date");
//                int store_id = rs.getInt("store_id");
//                int staff_id = rs.getInt("staff_id");
//                list.add(new order(order_id, customer_id, order_status, order_date, required_date, shipped_date, store_id, staff_id));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // Handle the exception properly
//        }
//        for (order order : list) {
//            System.out.println(order); // Assuming you have overridden toString() method in Order class
//        }
//    }
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

    public Staff getStaffWithid(int id) {
        Staff staff = null;
        String sql = "select * from staffs where staff_id = " + id;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
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
                String password1 = rs.getString("password");
                staff = new Staff(staff_id, first_name, last_name, email, phone, active, store, manager_id, password1);
            }

            return staff;
        } catch (SQLException e) {

        }
        return null;

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

    public ArrayList<order> getAll() {
        ArrayList<order> list = new ArrayList<>();
        String sql = "select * from Orders";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
                Staff staff = getStaffWithid(staff_id);
                list.add(new order(orderId, cus, orderStatus, orderDate, requiredDate, shippedDate, store, staff));
            }
            return list;
        } catch (SQLException e) {
        }
        return null;

    }

    public int UpdateStatus(order o) {
        int n = 0;
        String sql = "UPDATE [dbo].[orders]SET [order_status] = ? WHERE order_id = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, o.getOrderStatus());
            pre.setInt(2, o.getOrderId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
        }
        return n;
    }

    public static void main(String[] args) {
// Tạo một đối tượng mới của lớp Customer

// Tạo một đối tượng mới của lớp Store
        OrderDao dao = new OrderDao();
        System.out.println(dao.getNewOrderID());
//        ArrayList<order> list = dao.getAll();
//        System.out.println(dao.getCustomerWithId(1));
//        System.out.println(dao.GetOrderWithId(1));
//        order ord = list.get(1);
//        ord.setOrderStatus(3);
//        int n = dao.UpdateStatus(ord);
//        if (n > 0) {
//            System.out.println("succ");
//        } else {
//            System.out.println("nnnn");
//        }
//for (order ord:list){
//    System.out.println(ord);
//}

    }
}
