/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Statement;
import java.sql.ResultSet;
import entity.Product;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class ProductDao extends DBConnect {

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

    public ArrayList<Product> search(String text) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "select * from products where product_name LIKE '%" + text + "%'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int model_year = rs.getInt("model_year");
                double list_price = rs.getDouble("list_price");
                String brand_name = rs.getString("brand_name");
                String category_name = rs.getString("category_name");
                int status = rs.getInt("status");
                Product pro = new Product(product_id, product_name, model_year, list_price, brand_name, category_name, status);
                list.add(pro);

            }
            return list;
        } catch (SQLException ex) {
        }
        return null;

    }

    public ArrayList<Product> GetAllProductWithbrandname(String name) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "select * from products where brand_name = '" + name + "'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int model_year = rs.getInt("model_year");
                double list_price = rs.getDouble("list_price");
                String brand_name = rs.getString("brand_name");
                String category_name = rs.getString("category_name");
                int status = rs.getInt("status");
                Product pro = new Product(product_id, product_name, model_year, list_price, brand_name, category_name, status);
                list.add(pro);

            }
            return list;
        } catch (SQLException ex) {
        }
        return null;

    }

    public int removeProduct(int productID) {
        int n = 0;
        ResultSet rsOrder_item = this.getData("select * from order_items where product_id=" + productID);
        try {
            if (rsOrder_item.next()) {
                return n;
            }
        } catch (SQLException ex) {
        }
        /////////////////////////////////////////
        ResultSet rsStock = this.getData("select * from stocks where product_id=" + productID);
        try {
            if (rsStock.next()) {
                return n;
            }
        } catch (SQLException ex) {

        }
        String sql = "delete from products where product_id=" + productID;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {

        }

        return n;
    }

    public ArrayList<String> getAllCategoryNames() {
        ArrayList<String> categoryNames = new ArrayList<>();
        String sql = "SELECT DISTINCT brand_name FROM products";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String brand_name = resultSet.getString("brand_name");
                categoryNames.add(brand_name);
            }
        } catch (SQLException ex) {
            // Xử lý ngoại lệ nếu cần
            ex.printStackTrace();
        }
        return categoryNames;
    }

    public ArrayList<Product> DisplayAll() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "select * from products";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int model_year = rs.getInt("model_year");
                double list_price = rs.getDouble("list_price");
                String brand_name = rs.getString("brand_name");
                String category_name = rs.getString("category_name");
                int status = rs.getInt("status");
                Product pro = new Product(product_id, product_name, model_year, list_price, brand_name, category_name, status);
                list.add(pro);

            }
            return list;
        } catch (SQLException ex) {
        }
        return null;

    }

    public ArrayList<Product> DisplayAllcate() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "select * from products where ca";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int model_year = rs.getInt("model_year");
                double list_price = rs.getDouble("list_price");
                String brand_name = rs.getString("brand_name");
                String category_name = rs.getString("category_name");
                int status = rs.getInt("status");
                Product pro = new Product(product_id, product_name, model_year, list_price, brand_name, category_name, status);
                list.add(pro);

            }
            return list;
        } catch (SQLException ex) {
        }
        return null;

    }

    public int addPRoduct(Product obj) {
        int n = 0;
        String sql = "INSERT INTO [products]\n"
                + "           ([product_id],[product_name],[model_year],[list_price],[brand_name],[category_name],[status])\n"
                + "     VALUES\n"
                + "           (" + obj.getProduct_id() + ",'" + obj.getProduct_name() + "','" + obj.getModel_year() + "','" + obj.getList_price() + "','" + obj.getBrand_name() + "'," + obj.getCategory_name() + "'," + obj.getStatus() + ")";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
        }

        return n;
    }

    public int updateProduct(Product p) {
        int n = 0;
        String sql = "UPDATE [dbo].[products]\n"
                + "   SET [product_name] = ?,[model_year] = ? ,[list_price] = ?\n"
                + "      ,[brand_name] = ?,[category_name] = ?,[status]=?\n"
                + " WHERE [product_id] = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, p.getProduct_name());
            pre.setInt(2, p.getModel_year());
            pre.setDouble(3, p.getList_price());
            pre.setString(4, p.getBrand_name());
            pre.setString(5, p.getCategory_name());
            pre.setInt(6, p.getStatus());
            pre.setInt(7, p.getProduct_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {

        }

        return n;
    }

    public static void main(String[] args) {
        ProductDao dao = new ProductDao();
        ArrayList<Product> list = dao.search("wed");
        for (Product pro : list) {
            System.out.println(pro);
        }
    }
}
