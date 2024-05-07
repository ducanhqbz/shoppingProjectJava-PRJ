/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
import entity.Store;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class StoreDao extends DBConnect {

    public Store getStoreHaveProduct(int id) {
        Store store = null;
        String sql = "SELECT s.store_id, s.store_name, s.phone, s.email, s.street, s.city, s.state, s.zip_code\n"
                + "FROM stores s\n"
                + "INNER JOIN stocks st ON s.store_id = st.store_id\n"
                + "WHERE st.quantity > 0 and st.product_id = "+id;
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

    public ArrayList<Store> displayall() {
        ArrayList<Store> list = new ArrayList<>();
        String sql = "select * from Stores";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int Store_id = rs.getInt("store_id");
                String store_name = rs.getString("store_name");

                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String stateStore = rs.getString("state");
                String zip_code = rs.getString("zip_code");
                list.add(new Store(Store_id, store_name, phone, email, street, city, stateStore, zip_code));
            }
            return list;
        } catch (SQLException e) {

        }
        return null;
    }

    public static void main(String[] args) {
        StoreDao dao = new StoreDao();
        dao.displayall();
    }
}
