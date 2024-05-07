/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import entity.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Vector;
import model.CustomerDao;
import model.DaoStaff;

/**
 *
 * @author Acer
 */
public class listcustomer extends HttpServlet {

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
            out.println("<title>Servlet listcustomer</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listcustomer at " + request.getContextPath() + "</h1>");
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
        CustomerDao dao = new CustomerDao();
        ArrayList<Customer> list = new ArrayList<>();
        list = dao.displayall();
        String x = (String) request.getAttribute("error");
        if (x != null) {
            request.setAttribute("error1", x);
        }

        request.setAttribute("listcustomer", list);
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
        CustomerDao dao = new CustomerDao();
        String action = request.getParameter("action");
        if (action.isEmpty()) {
            response.sendRedirect("listcustomer");
        }
        if (action.equalsIgnoreCase("delete")) {
            String id = request.getParameter("id");
            int id_delete = Integer.parseInt(id);
            int n = dao.removeCustomer(id_delete);
            response.sendRedirect("listcustomer");
        }
        if (action.equalsIgnoreCase("edit")) {
            String id = request.getParameter("id");
            int id_edit = Integer.parseInt(id);
            Customer cus = dao.getCustomerWithId(id_edit);
            request.setAttribute("customeredit", cus);
            request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
        }
        if (action.equalsIgnoreCase("update")) {
            String customerId = request.getParameter("customer_id");
            int id = Integer.parseInt(customerId);
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zipCode = request.getParameter("zipCode");
            String password = request.getParameter("password");
            Customer cus = new Customer(id, firstName, lastName, phone, email, street, city, state, zipCode, password);
            int n = dao.UpdateCustomer(cus);
            response.sendRedirect("listcustomer");
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
