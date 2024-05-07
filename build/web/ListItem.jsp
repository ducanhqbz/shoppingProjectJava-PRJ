<%-- 
    Document   : Lisitem
    Created on : Apr 18, 2024, 9:38:00 PM
    Author     : Acer
--%>

<%@page import="entity.Order_Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.OrderItem_Dao"%>
<%@page import="entity.order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /* Reset CSS */
            body, h1, table {
                margin: 0;
                padding: 0;
            }

            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
            }

            h1 {
                text-align: center;
                margin: 20px 0;
            }

            .login, .logout {
                float: right;
                margin-right: 20px;
                margin-top: 20px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
            }

            .dropdown {
                position: relative;
                display: inline-block;
                margin-bottom: 20px;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
                z-index: 1;
                border: 1px solid #ddd;
                border-radius: 4px;
                padding: 8px;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .bill-table-container {
                float: right;
                width: 50%;
                margin-right: 20px;
                margin-top: 20px;
            }

            .bill-table {
                width: 100%;
                border-collapse: collapse;
            }

            .bill-table th, .bill-table td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            .bill-table th {
                background-color: #f2f2f2;
            }

            .bill-table tbody tr:nth-child(even) {
                background-color: #f9f9f9;
            }

        </style>
    </head>
    <body>
        <h1>User Management</h1>
        <div class="login">
            <a href="login.jsp">Login</a>
        </div>
        <c:if test="${not empty sessionScope.staff}">
            <div class="logout">
                <a href="logoutadmin">Logout</a>
            </div>
        </c:if>
        <table>
            <thead>
                <tr>
                    <th>Roll Number</th>
                    <th>Full Name</th>
                    <th>Welcome</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${not empty sessionScope.staff}">
                    <tr>
                        <td>${sessionScope.staff.staff_id}</td>
                        <td>${sessionScope.staff.first_name}</td>
                        <td>${sessionScope.staff.email}</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
        <div class="dropdown">
            <a href="#">User Options</a>
            <div class="dropdown-content">
                <a href="listcustomer">Customer Manager</a>
                <a href="listproduct">Product Manager</a>
                <a href="listbill">Bill Manager</a>
            </div>
        </div>
        <table style="width:100%">
            <tr>
                <th>Bill ID</th>
                <th>Customer Name</th> 
                <th>Total</th>
                <th>Status</th>
                <th>View</th>
            </tr>

             
            <%
                OrderItem_Dao dao = new OrderItem_Dao();
                order order = (order) request.getAttribute("billitem");

                ArrayList<Order_Item> list = dao.getAllWithOrderID(order.getOrderId());
                double total = 0;

                for (Order_Item item : list) {
                    int quantity = item.getQuantity();
                    double listPrice = item.getListPrice();
                    double subtotal = quantity * listPrice;
                    total += subtotal;
                }
            %>
            <tr>
                <td><%=order.getOrderId()%></td>
                <td><%=order.getCustomer().getFirstName()%> <%=order.getCustomer().getLastName()%></td>
                <td><%=total%></td>
                <td>
           <form method="POST" action="listbill">
    <select id="id" name="IdStatus">
        <option value="1" <%= order.getOrderStatus() == 1 ? "selected" : "" %>>Done</option>
        <option value="2" <%= order.getOrderStatus() == 2 ? "selected" : "" %>>Processing</option>
        <option value="0" <%= order.getOrderStatus() == 0 ? "selected" : "" %>>Waiting</option>
        <!-- Adjusted to ensure there's an option for "Waiting" status when status is 3 or any unexpected value -->
        <option value="3" <%= (order.getOrderStatus() == 3 || order.getOrderStatus() > 3) ? "selected" : "" %>>Waiting</option>
    </select>
    <input type="hidden" name="action" value="changestatus">
    <input type="hidden" name="orderID" value="<%=order.getOrderId()%>">
    <input type="submit" value="Change" />
</form>

                    <input type="submit" value="Change" />
                </td>
            </tr>
        </table>
        <div class="bill-table-container">
            <!-- Đặt bảng bill ở đây -->
            <table class="bill-table">
                <thead>
                    <tr>
                        <th>Item_ID</th>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>List Price</th>
                        <th>Discount</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.item}" var="item">
                        <tr>
                            <td>${item.itemId}</td>
                            <td>${item.product.getProduct_name()}</td>
                            <td>${item.quantity}</td>
                            <td>${item.listPrice}</td>
                            <td>${item.discount}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
