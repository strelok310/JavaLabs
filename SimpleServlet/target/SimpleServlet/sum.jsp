<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Adding of two numbers</title>
</head>
<body>
    <form method="post">
        <h1>Enter two numbers to get their sum:</h1>
        <label>First Number:
            <input type="number" name="value_a"></input>
        </label>
        <br>
        <label>Second Number:
            <input type="number" name="value_b"></input>
        </label>
        <br>
        <button type="submit">Calculate</button>
    </form>
    <br>
    <hr>
    <br>
    <h1>Result:
    <%
        String str = (String) request.getAttribute("result");
            if(str != null) out.println(str);
    %></h1>

</body>
</html>