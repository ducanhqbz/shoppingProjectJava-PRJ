<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Customer</title>
</head>
<body>
    <h2>Add New Customer</h2>
    <form action="listcustomer" method="post">
         <label for="firstName">Customer id</label><br>
         <input type="text" id="firstName" name="customer_id" readonly value="${requestScope.customeredit.customerId}"><br>
        <label for="firstName">First Name:</label><br>
        <input type="text" id="firstName" name="firstName" value="${requestScope.customeredit.firstName}"><br>
        
        <label for="lastName">Last Name:</label><br>
        <input type="text" id="lastName" name="lastName" value="${requestScope.customeredit.lastName}"><br>
        
        <label for="phone">Phone:</label><br>
        <input type="tel" id="phone" name="phone" value="${requestScope.customeredit.phone}"><br>
        
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" value="${requestScope.customeredit.email}"><br>
        
        <label for="street">Street:</label><br>
        <input type="text" id="street" name="street" value="${requestScope.customeredit.street}"><br>
        
        <label for="city">City:</label><br>
        <input type="text" id="city" name="city" value="${requestScope.customeredit.city}"><br>
        
        <label for="state">State:</label><br>
        <input type="text" id="state" name="state" value="${requestScope.customeredit.state}"><br>
        
        <label for="zipCode">Zip Code:</label><br>
        <input type="text" id="zipCode" name="zipCode" pattern="[0-9]{5}" value="${requestScope.customeredit.zipCode}"><br>
        
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" value="${requestScope.customeredit.password}"><br>
        <input type="hidden" name="action" value="update">
        <input type="submit" value="Submit">
    </form>
</body>
</html>
