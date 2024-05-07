///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package model;
//
//import entity.CacheTable;
//import java.sql.Statement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Vector;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author Administrator
// */
//public class CacheDAO extends DBConnect {
//
//    public Cache getCache() {
//        Cache cache = null;
//        try {
//            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            ResultSet rs = this.getData("Select * from CacheTable");
//            if (rs.next()) {
//                int CacheID = rs.getInt("CacheID");
//                int CProductID = rs.getInt("ProductID");
//                int COrderID = rs.getInt("OrderID");
//                int CCustomerID = rs.getInt("CustomerID");
//                int CStaffID = rs.getInt("StaffID");
//                int CStoreID = rs.getInt("StoreID");
//                cache = new Cache(CacheID, CProductID, COrderID, CCustomerID, CStaffID, CStoreID);
//            }
//        } catch (SQLException e) {
//
//        }
//        return cache;
//    }
////Ham Test 
//
//    public static void main(String[] args) {
//        CacheDAO cdb = new CacheDAO();
//        CacheDAO x = cdb.getCache();
//        System.out.println(x.());
//    }
//}