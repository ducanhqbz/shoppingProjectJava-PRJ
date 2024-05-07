

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style> body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 20px;
                color: #333;
            }
            h1.display-3 {
                margin-left: 0;
                color: #333;
            }
            table.table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: white;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            table.table th, table.table td {
                padding: 12px;
                border: 1px solid #ddd;
                text-align: left;
            }
            table.table th {
                background-color: #f8f8f8;
            }
            input[type="text"], input[type="submit"] {
                padding: 8px;
                margin: 4px 0;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #45a049;
            }</style>
    </head>
    <body>
          <a href="home">Back to home</a>
        <h1 class="display-3" style="margin-left:70px">Carts</h1>
        <div style="margin:40px">
       
            <table class="table" >
                <thead>
                    <tr>
      
                       
                        <th scope="col">Item ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">List Price</th>
                        <th scope="col">Discount</th>
                        <th scope="col">Delete From Cart</th>
                    </tr>
                </thead>
                <p>${requestScope.status}</p>
                <tbody>
                    <c:forEach var="a" items="${sessionScope.cart}" >
                        <tr>
                            <th scope="col">${a.itemId}</th>
                            <th scope="col">${a.product.getProduct_name()}</th>
                           
                            <th scope="col"><input type="text" name="quantity" value="${a.quantity}"></th>
                            <th scope="col">${a.listPrice*a.quantity}$</th>
                            <th scope="col">${a.discount}</th>
                            <th scope="col">
                                <form method="post"action="cart">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="cartdelete" value="${a.itemId}">
                                    <button style="border:none; background-color: white; color:orange" onclick="if(confirm('Do you want to delete?')) {alert('Item deleted.'); } else { alert('Deletion cancelled.'); }">Delete From The Cart</button>
                                </form>
                            </th>
                        </tr>
                    </c:forEach>
                </tbody>
              
            </table>
        </div>
                <form action="removeallcart" method="post">
                    <input type="submit" value="Remove All" />
                    
                </form>
                  <form>
                    <input type="submit" value="Update" />
                     
                </form>
                <form action="checkout" method="post">
                    <input type="submit" value="CheckOut" />
                    
                </form>
    </body>
</html>