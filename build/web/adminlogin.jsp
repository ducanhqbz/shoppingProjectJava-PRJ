<%@page import="entity.order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Order_Item"%>
<%@page import="model.OrderItem_Dao"%>
<%@page import="model.OrderDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Management</title>
        <style>
            /* Reset CSS */
            body, h1, h2, h3, p, ul, ol, li, table, th, td {
                margin: 0;
                padding: 0;
            }

            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
            }

            .container {
                max-width: 1042px;
                margin: 20px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            h1 {
                text-align: center;
                margin-bottom: 20px;
            }

            .login, .logout {
                float: right;
                margin-left: 10px;
            }

            .table-container {
                margin-bottom: 20px;
            }

            .table-container table {
                width: 100%;
                border-collapse: collapse;
            }

            .table-container th, .table-container td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            .table-container th {
                background-color: #f2f2f2;
            }

            .table-container tr:nth-child(even) {
                background-color: #f9f9f9;
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
                width: 50%; /* ?i?u ch?nh kích th??c b?ng tùy ý */
                margin-left: 20px; /* Kho?ng cách gi?a b?ng và n?i dung khác */
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

            .bill-table tbody tr:hover {
                background-color: #f2f2f2;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <h1>User Management</h1>
            <div class="login">
                <a href="login.jsp">Login</a>
            </div>
            <c:if test="${not empty sessionScope.staff}">
                <div class="logout">
                    <a href="logoutadmin">Logout</a></c:if>
                </div>
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
                            <td>${sessionScope.staff.email}</td> </c:if>
                        </tr>
                    </tbody>
                </table>
                <div class="dropdown">
                    <a href="#">User Options</a>
                    <div class="dropdown-content">
                        <a href="listcustomer">Customer Manager</a>
                        <a href="listproduct">Product Manager</a>
                        <a href="listbill">Bill Manager</a>
                    </div>
                    <div>
                        <p>${requestScope.error1}</p>
                    <c:if test="${not empty sessionScope.staff}">
                        <c:if test="${not empty requestScope.listcustomer}">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>customerId</th>
                                        <th>firstName</th>
                                        <th>lastName</th>
                                        <th>email</th>
                                        <th>phone</th>
                                        <th>street</th>
                                        <th>city</th>
                                        <th>state</th>
                                        <th>zipCode</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.listcustomer}" var="customer">
                                        <tr>
                                            <td>${customer.customerId}</td>
                                            <td>${customer.firstName}</td>
                                            <td>${customer.lastName}</td>
                                            <td>${customer.email}</td>
                                            <td>${customer.phone}</td>
                                            <td>${customer.street}</td>
                                            <td>${customer.city}</td>
                                            <td>${customer.state}</td>
                                            <td>${customer.zipCode}</td>
                                            <td>
                                              
                                                <form method="post" action="listcustomer">
                                                    <input type="hidden" value="delete" name="action">
                                                    <input type="hidden" name="id" value="${customer.customerId}">
                                                    <input type="submit" value="Delete" />
                                                </form>
                                            </td>
                                            <td>   <form method="post" action="listcustomer">
                                                    <input type="hidden" value="edit" name="action">
                                                    <input type="hidden" name="id" value="${customer.customerId}">
                                                    <input type="submit" value="Edit" />
                                                </form></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${not empty requestScope.product}">  
                            <table style="width:100%">
                                <tr>
                                    <th>Name</th>
                                    <th>Model</th> 
                                    <th>Price</th>
                                    <th>New Product</th>
                                    <th>Action</th>
                                </tr>
                                <c:forEach var="product" items="${product}">
                                    <tr>
                                        <td>${product.product_name}</td>
                                        <td>${product.model_year}</td>
                                        <td>${product.list_price}</td>
                                        <td>${product.category_name}</td>
                                        <td>
                                            <form method="post" action="listproduct">
                                                <input type="hidden" value="edit" name="action">
                                                <input type="hidden" name="id" value="${product.product_id}">
                                                <input type="submit" value="Edit" />
                                            </form> 
                                            <form method="post" action="listproduct">
                                                <input type="hidden" value="delete" name="action">
                                                <input type="hidden" name="id" value="${product.product_id}">
                                                <input type="submit" value="Delete" />
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                        <c:if test="${not empty requestScope.bill}">
                            <table style="width:100%">
                                <tr>
                                    <th>Bill ID</th>
                                    <th>Customer Name</th> 
                                    <th>Total</th>
                                    <th>Status</th>
                                    <th>View</th>
                                </tr>
                                <%
                                    ArrayList<order> listorder = (ArrayList<order>) request.getAttribute("bill");
                                    for (order order : listorder) {
                                        OrderItem_Dao dao = new OrderItem_Dao();
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
                                      <%
    int status = order.getOrderStatus();
    String statusText = "";
    if (status == 1) {
        statusText = "Done";
    } else if (status == 2) {
        statusText = "Processing";
    } else if (status == 3 || status == 0) {
        statusText = "Waiting";
    } else {
        statusText = "Waiting"; // Ho?c thông báo l?i khác n?u c?n
    }
    out.println(statusText);
%>
                                    <td>
                                        <form method="post" action="listbill">
                                            <input type="hidden" value="view" name="action">
                                            <input type="hidden" name="orderID" value="<%=order.getOrderId()%>">
                                            <input type="submit" value="view" />
                                        </form>
                                    </td>
                                </tr>
                                <% }%>
                            </table>
                  

                        </c:if>

                    </c:if>

                </div>

            </div>          

        </div>
    </body>
</html>
