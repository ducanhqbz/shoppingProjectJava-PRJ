<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Web Page Example</title>
        <style>
            /* Add your styles here */
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
            th, td {
                padding: 10px;
            }
            th {
                text-align: left;
            }
            .header {
                overflow: hidden;
                background-color: #f1f1f1;
                padding: 20px 10px;
            }
            .header a {
                float: left;
                color: black;
                text-align: center;
                padding: 12px;
                text-decoration: none;
                font-size: 18px;
                line-height: 25px;
                border-radius: 4px;
            }
            .header a.logo {
                font-size: 25px;
                font-weight: bold;
            }
            .header a:hover {
                background-color: #ddd;
                color: black;
            }
            .header a.active {
                background-color: dodgerblue;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <c:if test="${not empty sessionScope.customer}">
                <p>${sessionScope.customer.firstName} ${sessionScope.customer.lastName} - Welcome: ${sessionScope.customer.email}</p>
            </c:if>
        </div>
        <div style="float:right;">
            <c:if test="${empty sessionScope.customer}">
                <a href="login.jsp">Login</a>
            </c:if>
            <c:if test="${not empty sessionScope.customer}">
                <a href="Logout">Logout</a>
            </c:if>
            <c:if test="${ empty sessionScope.customer}">
                <a href="Register.jsp">Register</a>
            </c:if>
            <c:if test="${ not empty sessionScope.customer}">   
                <a href="addtoCart.jsp">Show Cart</a>    
            </c:if>
        </div>
        <form method="post" action="home">
            <select name="productId">
                <c:forEach var="brand_name" items="${brand_name}">
                    <option value="${brand_name}">${brand_name}</option>
                </c:forEach>
                    <option value="">All</option>
                    <input type="submit" value="Find" />
            </select>
        </form>
        <form method="get" action="searchitem">
            <input type="text" name="searchText" placeholder="Search by name">
            <input type="submit" value="Search">
        </form>
           
      <table style="width:100%">
    <tr>
        <th>Name</th>
        <th>Model</th>
        <th>Price</th>
        <th>New Product</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <p>${sessionScope.error}</p>
    <c:forEach var="product" items="${requestScope.product}">
        <tr>
            <td>${product.product_name}</td>
            <td>${product.model_year}</td>
            <td>${product.list_price}</td>
            <td>${product.category_name}</td>
            <td>
                <c:choose>
                    <c:when test="${product.status == 0}">
                        Unavailable
                    </c:when>
                    <c:when test="${product.status == 1}">
                        Available
                    </c:when>
                </c:choose>
            </td>   
            <td>
                <c:if test="${product.status == 1}">
                    <form method="POST" action="cart">
                        <input type="hidden" name="action" value="addtocart">
                        <input type="hidden" name="product_id" value="${product.product_id}">
                        <input type="submit" value="Add to Cart" />
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
         x
    </body>
</html>
