/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
import entity.Staff;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.util.Types;

/**
 *
 * @author Acer
 */
public class CustomerDao extends DBConnect {

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

    public int UpdateCustomer(Customer obj) {
        int n = 0;
        String sql = "UPDATE [dbo].[customers]\n"
                + "   SET \n"
                + "      [first_name] = ?\n"
                + "      ,[last_name] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[street] = ?\n"
                + "      ,[city] = ?\n"
                + "      ,[state] = ?\n"
                + "      ,[zip_code] = ?,[password]=?\n"
                + " WHERE [customer_id] =?   ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(9, obj.getPassword());
            pre.setString(1, obj.getFirstName());
            pre.setString(2, obj.getLastName());
            pre.setString(3, obj.getPhone());
            pre.setString(4, obj.getEmail());
            pre.setString(5, obj.getStreet());
            pre.setString(6, obj.getCity());
            pre.setString(7, obj.getState());
            pre.setString(8, obj.getZipCode());
            pre.setInt(10, obj.getCustomerId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            // Xử lý ngoại lệ ở đây (ví dụ: ghi log hoặc báo cáo lỗi)
            ex.printStackTrace();
        }

        return n;
    }

    public Customer loginCustomer(String email, String password) {
        String sql = "select * from customers where email= '" + email + "' and password = '" + password + "'";
        Customer cus = null;
        System.out.println(sql);
        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                int customerId = rs.getInt("customer_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email_customer = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zipCode = rs.getString("zip_code");
                String password1 = rs.getString("password");
                cus = new Customer(customerId, firstName, lastName, phone, email, street, city, state, zipCode, password1);

            }

        } catch (SQLException e) {
        }
        return cus;
    }

    public int removeCustomer(int CustomerID) {
        int n = 0;
        ResultSet rsOrder = this.getData("select * from orders where customer_id=" + CustomerID);
        try {
            if (rsOrder.next()) {
                return n;
            }
        } catch (SQLException ex) {

        }

        String sql = "delete from customers where customer_id=" + CustomerID;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {

        }

        return n;
    }

    public int addCustomer(Customer obj) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[customers]\n"
                + "           ([customer_id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[street]\n"
                + "           ,[city]\n"
                + "           ,[state]\n"
                + "           ,[zip_code],[password])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?,?)";
        String sql2 = "SELECT MAX(customer_id) as customer_id FROM customers";
        int count = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql2);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                count = rs.getInt("customer_id");
                count++;
            }
            System.out.println(count);
        } catch (Exception e) {
        }

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, count);
            pre.setString(2, obj.getFirstName());
            pre.setString(3, obj.getLastName());
            if (obj.getPhone() == null) {
                // Set phone to null or some default value, depending on your database schema
                pre.setNull(4, java.sql.Types.NULL); // Set phone to null
                // Alternatively, you can set it to an empty string
                // pre.setString(4, ""); // Set phone to an empty string
            } else {
                pre.setString(4, obj.getPhone());
            }

            pre.setString(5, obj.getEmail());
            pre.setString(6, obj.getStreet());
            pre.setString(7, obj.getCity());
            pre.setString(8, obj.getState());
            pre.setString(9, obj.getZipCode());
            pre.setString(10, obj.getPassword());
            n = pre.executeUpdate();
        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return n;
    }

    public ArrayList<Customer> displayall() {
        ArrayList<Customer> list = new ArrayList<>();
        String sql = "select * from Customers";
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
                list.add(new Customer(customer_id, first_name, last_name, phone, email, street, city, stateCus, zip_code, password));
            }
            return list;
        } catch (SQLException e) {

        }
        return null;
    }

    public static void main(String[] args) {

        CustomerDao dao = new CustomerDao();
//        Customer cus = dao.loginCustomer("caren.stephens@msn.com");
//Customer customer = new Customer(
//    /* customer_id */ 50001, 
//    /* first_name */ "John", 
//    /* last_name */ "Doe", 
//    /* phone */ "1234567890", 
//    /* email */ "john.doe@example.com", 
//    /* street */ "123 Main St", 
//    /* city */ "Anytown", 
//    /* state */ "CA", 
//    /* zip_code */ "12345"
//);

//   dao.addCustomer(customer);
//              
//     ArrayList<Customer> list = dao.displayall();
//    if (dao.addCustomer(customer)>0){
//        System.out.println("success");
//    } else {System.out.println("loz");}
    }
}
