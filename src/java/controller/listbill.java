/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Order_Item;
import entity.order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.OrderDao;
import model.OrderItem_Dao;

/**
 *
 * @author Acer
 */
public class listbill extends HttpServlet {

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
            out.println("<title>Servlet listbill</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listbill at " + request.getContextPath() + "</h1>");
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
        OrderDao dao = new OrderDao();
        ArrayList<order> listorder = dao.getAll();
        request.setAttribute("bill", listorder);
        request.getRequestDispatcher("adminlogin.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String orderID = request.getParameter("orderID");
    String action = request.getParameter("action");
    
    if (orderID != null && action != null) {
        try {
            int id = Integer.parseInt(orderID);
            OrderDao dao1 = new OrderDao();
            OrderItem_Dao dao = new OrderItem_Dao();
            order order = dao1.GetOrderWithId(id);
            request.setAttribute("billitem", order);
            ArrayList<Order_Item> item = dao.getAllWithOrderID(id);
            request.setAttribute("item", item);
            
            if (action.equalsIgnoreCase("changestatus")) {
                String IdStatus_raw = request.getParameter("IdStatus");
                int IDStatus = Integer.parseInt(IdStatus_raw);
                order order1 = dao1.GetOrderWithId(id);
                order1.setOrderStatus(IDStatus);
                int n = dao1.UpdateStatus(order1);
            }
        } catch (NumberFormatException e) {
            // Xử lý ngoại lệ khi không thể chuyển đổi thành số nguyên
            e.printStackTrace();
        }
    }
    
    request.getRequestDispatcher("ListItem.jsp").forward(request, response);
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
