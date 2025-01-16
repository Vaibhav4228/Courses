  <!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
  Welcome to login page
  <pre> ${errorMessage} </pre>
    <form action="login" method="POST">

 <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>

        <label for="password">Password:</label>
                   <input type="password" id="password" name="password" required><br><br>

        <button type="submit">Submit</button>
    </form>
         </body>
</html>
