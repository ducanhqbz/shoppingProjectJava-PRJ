/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.CustomerDao;
import model.ProductDao;

/**
 *
 * @author Acer
 */
public class listproduct extends HttpServlet {

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
            out.println("<title>Servlet listproduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listproduct at " + request.getContextPath() + "</h1>");
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
        ProductDao dao = new ProductDao();
        ArrayList<Product> list = dao.DisplayAll();
        ArrayList<String> brand_name = dao.getAllCategoryNames();
        request.setAttribute("brand_name", brand_name);
        request.setAttribute("product", list);

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
        ProductDao dao = new ProductDao();
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            String id = request.getParameter("id");
            int id_delete = Integer.parseInt(id);
            int n = dao.removeProduct(id_delete);

            response.sendRedirect("listproduct");
        }
        if (action.equalsIgnoreCase("edit")) {
            String id = request.getParameter("id");
            int id_edit = Integer.parseInt(id);
            Product product = dao.getProductWithID(id_edit);
            request.setAttribute("productedit", product);
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
        }
        if (action.equalsIgnoreCase("update")) {
            String productId = request.getParameter("productid");
            int product_id = Integer.parseInt(productId);
            String productName = request.getParameter("productName");
            String modelYear = request.getParameter("modelYear");
            int model = Integer.parseInt(modelYear);
            String listPrice = request.getParameter("listPrice");
            double price = Double.parseDouble(listPrice);
            String brandName = request.getParameter("brandName");
            String categoryName = request.getParameter("categoryName");
            String status = request.getParameter("status");
            int status_f = Integer.parseInt(status);
            Product product = new Product(product_id, productName, model, price, brandName, categoryName, status_f);
            dao.updateProduct(product);
            response.sendRedirect("listproduct");
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
