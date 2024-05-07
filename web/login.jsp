<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        h1 {
            color: #333;
        }

        form {
            max-width: 300px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        p.error {
            color: red;
            margin-top: 10px;
        }

        a {
            text-decoration: none;
            color: #007bff;
            margin-top: 10px;
            display: block;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <a href="home">Back to home</a>
    <a href="Register.jsp">Register</a>
    <h1>Login</h1>
    <form method="post" action="Login">
        <label for="email">Email:</label><br>
        <input type="text" id="email" name="email" required><br>
          <label for="email">PassWord:</label><br>
              <input type="text" id="email" name="password" required><br>
        <input type="submit" value="Login">
    </form>
    <p class="error">${requestScope.error}</p>   
</body>
</html>
