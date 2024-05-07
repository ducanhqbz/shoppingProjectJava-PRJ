/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import entity.Order_Item;
import entity.Product;
import entity.Store;
import entity.order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.DaoStaff;
import model.OrderDao;
import model.OrderItem_Dao;
import model.ProductDao;
import model.StoreDao;
import org.apache.catalina.servlets.DefaultServlet;

/**
 *
 * @author Acer
 */
public class cart extends HttpServlet {

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
            out.println("<title>Servlet cart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet cart at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        OrderDao orderDao = new OrderDao();
        StoreDao storedao = new StoreDao();
        DaoStaff staffdao = new DaoStaff();
        OrderItem_Dao itemdao = new OrderItem_Dao();
        ProductDao productDao = new ProductDao();
        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            String id = req.getParameter("cartdelete");
            int id1 = Integer.parseInt(id);
            ArrayList<Order_Item> cart = (ArrayList<Order_Item>) session.getAttribute("cart");
            for (Order_Item item : cart) {
                if (item.getItemId() == id1) {
                    itemdao.removeOrderItem(item.getIdorder(), id1);
                    resp.sendRedirect("addtoCart.jsp");
                }
            }

        }
        if (action.equalsIgnoreCase("addtocart")) {
            if (session.getAttribute("customer") == null) {
                resp.sendRedirect("login.jsp");
                return; // Return to prevent further processing
            }

            ArrayList<Order_Item> cart = (ArrayList<Order_Item>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
                session.setAttribute("cart", cart);
            }

            String product_id_raw = req.getParameter("product_id");
            int product_id = Integer.parseInt(product_id_raw);
            boolean productExistsInCart = false;

            for (Order_Item x : cart) {
                if (x.getProduct().getProduct_id() == product_id) {
                    x.setQuantity(x.getQuantity() + 1);
                    productExistsInCart = true;
                    break;
                }
            }

            if (!productExistsInCart) {
                Product product = productDao.getProductWithID(product_id);
                if (product.getStatus() == 0) {
                    session.setAttribute("error", "Da het san pham");
                    resp.sendRedirect("home");
                    return; // Return to prevent further processing
                }
                if (product != null) {
                    int newItemId = 0;
                    for (Order_Item item : cart) {
                        if (item.getItemId() > newItemId) {
                            newItemId = item.getItemId();
                        }
                    }

                    Order_Item newItem = new Order_Item(orderDao.getNewOrderID(), newItemId + 1, product, 1, product.getList_price(), 0);
                    cart.add(newItem);
                }
            }

            resp.sendRedirect("home"); // Move this line here
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
