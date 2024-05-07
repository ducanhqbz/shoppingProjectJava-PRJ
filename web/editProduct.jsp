<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
</head>
<body>
    <h2>Add New Product</h2>
    <form action="listproduct" method="post">
        <label for="productName">Product iD:</label><br>
        <input type="text" id="productName" name="productid" value="${requestScope.productedit.product_id}" readonly><br>
        
        <label for="productName">Product Name:</label><br>
        <input type="text" id="productName" name="productName" value="${requestScope.productedit.product_name}" ><br>
        
        <label for="modelYear">Model Year:</label><br>
        <input type="text" id="modelYear" name="modelYear" value="${requestScope.productedit.model_year}"><br>
        
        <label for="listPrice">List Price:</label><br>
        <input type="text" id="listPrice" name="listPrice" value="${requestScope.productedit.list_price}"><br>
        
        <label for="brandName">Brand Name:</label><br>
        <input type="text" id="brandName" name="brandName" value="${requestScope.productedit.brand_name}"><br>
        
        <label for="categoryName">Category Name:</label><br>
        <input type="text" id="categoryName" name="categoryName" value="${requestScope.productedit.category_name}"><br>
        
        <label for="status">Status:</label><br>
        <select id="status" name="status" required>
            <option value="1">Active</option>
            <option value="0">Inactive</option>
        </select><br><br>
        <input type="hidden" name="action" value="update">
        <input type="submit" value="Submit">
    </form>
</body>
</html>
