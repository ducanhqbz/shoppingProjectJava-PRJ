/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Order_Item;
import entity.Product;
import entity.Stock;
import entity.Store;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Acer
 */
public class StockDao extends DBConnect {

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

    public ArrayList<Stock> displayall() {
        ArrayList<Stock> list = new ArrayList<>();
        String sql = "select * from Stocks";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int store_id = rs.getInt("store_id");
                Store store = getStorewithid(store_id);
                int product_id = rs.getInt("product_id");
                Product product = getProductWithID(product_id);
                int quantity = rs.getInt("quantity");

                list.add(new Stock(store, product, quantity));
//               list.add(new Staff(staff_id, first_name, last_name, email, phone, active, store_id, manager_id));
            }
            return list;
        } catch (SQLException e) {

        }
        return null;
    }

    public static void main(String[] args) {
        StockDao dao = new StockDao();
        dao.displayall();
    }

}
