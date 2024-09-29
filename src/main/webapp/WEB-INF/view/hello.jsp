<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <h1>Hello from JSP</h1>
    <p>Today's date: <%= (new java.util.Date()).toLocaleString()%></p>
    <h1>${dao}</h1>
    <!--dao là định nghĩa key trong UserController-->
  </body>
</html>
