/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Staff;
import entity.Store;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Acer
 */
public class DaoStaff extends DBConnect {
public Staff getRandomStaff(){
    Staff staff = null;
String sql ="SELECT TOP 1 *\n" +
"FROM staffs\n" +
"ORDER BY NEWID()";
       try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                int staff_id = rs.getInt("staff_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email1 = rs.getString("email");
                String phone = rs.getString("phone");
                int active = rs.getInt("active");
                int store_id = rs.getInt("store_id");
                Store store = getStorewithid(store_id);

                int manager_id = rs.getInt("manager_id");
                String password1 = rs.getString("password");
                staff = new Staff(staff_id, first_name, last_name, email1, phone, active, store, manager_id, password1);
            }

            return staff;
        } catch (SQLException e) {

        }
        return null;

    }
//    public void displayall() {
//             ArrayList<Staff> list = new ArrayList<>();
//        String sql = "select * from staffs";
//        try {
//            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = state.executeQuery(sql);
//                       while (rs.next()) {
//                int staff_id = rs.getInt("staff_id");
//                String first_name = rs.getString("first_name");
//                String last_name = rs.getString("last_name");
//                String email = rs.getString("email");
//                String phone = rs.getString("phone");
//                int active = rs.getInt("active");
//                int store_id = rs.getInt("store_id");
//                int manager_id = rs.getInt("manager_id");
//               list.add(new Staff(staff_id, first_name, last_name, email, phone, active, store_id, manager_id));
//            }
//        } catch (SQLException e) {
//
//        }
//        for (Staff staff : list ){
//            System.out.println(staff);
//        }
//    }
    public int addStaff(Staff obj) {
        int n = 0;
        String sql = "INSERT INTO [staffs]\n"
                + "           ([staff_id],[first_name],[last_name],[email],[phone],[active],[store_id],[manager_id])\n"
                + "     VALUES\n"
                + "           (" + obj.getStaff_id() + ",'" + obj.getFirst_name() + "','" + obj.getLast_name() + "','" + obj.getEmail() + "','" + obj.getPhone() + "'," + obj.getActive() + "," + obj.getStore().getStoreId() + "," + obj.getManager_id() + ")";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
        }

        return n;
    }

    public int updatePhoneStaff(int staff_id, String phone) {
        int n = 0;
        String sql = "  UPDATE [dbo].[staffs] SET"
                + "      [phone] = ?\n"
                + " WHERE staff_id = ? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(2, staff_id);

            pre.setString(1, phone);

            n = pre.executeUpdate();
        } catch (SQLException ex) {

        }
        return n;
    }

    public int updateStaff(Staff obj) {
        String sql = "  UPDATE [dbo].[staffs] SET [first_name] = ?,[last_name] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[active] = ?\n"
                + "      ,[store_id] = ?\n"
                + "      ,[manager_id] = ?\n"
                + " WHERE staff_id = ? ";
        int n = 0;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(8, obj.getStaff_id());
            pre.setString(1, obj.getFirst_name());
            pre.setString(2, obj.getLast_name());
            pre.setString(3, obj.getEmail());
            pre.setString(4, obj.getPhone());
            pre.setInt(5, obj.getActive());
            pre.setInt(6, obj.getStore().getStoreId());
            pre.setInt(7, obj.getManager_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {

        }
        return n;
    }

    public int insertStaff(Staff obj) {
        int n = 0;
        String sql = "INSERT INTO [staffs]\n"
                + "           ([staff_id],[first_name],[last_name],[email]\n"
                + "           ,[phone],[active],[store_id],[manager_id],[password])\n"
                + "     VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, obj.getStaff_id());
            pre.setString(2, obj.getFirst_name());
            pre.setString(3, obj.getLast_name());
            pre.setString(4, obj.getEmail());
            pre.setString(5, obj.getPhone());
            pre.setInt(6, obj.getActive());
            pre.setInt(7, obj.getStore().getStoreId());
            pre.setInt(8, obj.getManager_id());
            pre.setString(9, obj.getPassword());
            n = pre.executeUpdate();
        } catch (SQLException ex) {

        }
        return n;
    }

    public int removeStaff(int StaffID) {
        int n = 0;
        // check foreikey key order
        ResultSet rsOrder = this.getData("select * from orders where staff_id=" + StaffID);
        try {
            if (rsOrder.next()) {
                return n;
            }
        } catch (SQLException ex) {

        }
        // check manager
        ResultSet rsManager = this.getData("select * from staffs where " + StaffID + " in (select distinct manager_id from staffs)");
        try {
            if (rsManager.next()) {
                return n;
            }
        } catch (SQLException ex) {

        }

        String sql = "delete from staffs where staff_id=" + StaffID;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
            //change active = 0
        } catch (SQLException ex) {

        }

        return n;
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

    public Staff LoginStaff(String email, String password) {
        String sql = "select * from Staffs where email = '" + email + "' and password ='" + password + "'";
        Staff staff = null;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            if (rs.next()) {
                int staff_id = rs.getInt("staff_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email1 = rs.getString("email");
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

    public Vector<Staff> getAll(String sql) {
        Vector<Staff> list = new Vector<>();

        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
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
                Staff staff = new Staff(staff_id, first_name, last_name, email, phone, active, store, manager_id, password1);
                list.add(staff);
            }

            return list;
        } catch (SQLException e) {

        }
        return null;

    }

    public static void main(String[] args) {
        DaoStaff dao = new DaoStaff();
//        Staff staff_add = new Staff(15, "ditme", "nguyen", "email1@gmail.com", "0369721158", 1, 1, 1);
//
//        int n = dao.removeStaff(15);
//        if (n > 0) {
//            System.out.println("succes");
//        } else {
//            System.out.println("failed");
//        }
//        Vector<Staff> list = dao.getAll("select * from staffs");
//        for (Staff staff : list) {
//            System.out.println(staff);
//        }
//        Staff staff = dao.LoginStaff("fabiola.jackson@bikes.shop");
//        System.out.println(staff);
//DaoStaff dao = new DaoStaff();
//dao.displayall();
    }
}
