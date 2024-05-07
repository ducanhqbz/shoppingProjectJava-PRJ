/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import entity.Order_Item;
import entity.Staff;
import entity.Store;
import entity.order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import model.DaoStaff;
import model.OrderDao;
import model.OrderItem_Dao;
import model.StoreDao;
import org.apache.catalina.servlets.DefaultServlet;

/**
 *
 * @author Acer
 */
public class checkout extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet checkout</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checkout at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDao orderDao = new OrderDao();
        OrderItem_Dao itemDao = new OrderItem_Dao();
        DaoStaff staffDao = new DaoStaff();
        StoreDao storeDao = new StoreDao();
        HttpSession session = request.getSession(false);  // Handle null session
        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        ArrayList<Order_Item> cart = (ArrayList<Order_Item>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("addtoCart.jsp");
            return;
        }

        Customer cus = (Customer) session.getAttribute("customer");
        if (cus == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            LocalDate today = LocalDate.now();
            LocalDate requiredDate = today.plusDays(3);
            int orderId = orderDao.getNewOrderID();
            Store store = null;
            Staff staff = staffDao.getRandomStaff();
            for (Order_Item item : cart) {

                if (store == null) {
                    store = storeDao.getStoreHaveProduct(item.getProduct().getProduct_id());
                }

            }

            order newOrder = new order(orderId, cus, 0, today.toString(), requiredDate.toString(), null, store, staff);
            orderDao.addorder(newOrder);
            for (Order_Item item : cart) {
                item.setIdorder(orderId);
                int n = itemDao.addOrder_itemver2(item);

            }

            session.removeAttribute("cart");
            request.setAttribute("status", "Order Successfullll");
            request.getRequestDispatcher("addtoCart.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Database error occurred: " + e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
