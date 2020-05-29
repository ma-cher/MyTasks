
<%--
  Created by IntelliJ IDEA.
  User: M.Chernyavskaya
  Date: 27.05.2020
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyTasks</title>
</head>
<body>
<h2>Create a new Task</h2><br />
<% String login = (String) request.getAttribute("login"); %>

<form method="post" action="<c:url value='/create_task'/>">

    <label>Title</label><br>
    <input name="title"/><br>

    <label>Description</label><br>
    <input name="description"/><br>
    <input type="submit" value="Create"/>

    <% request.setAttribute("login", login);%>
</form>




</body>
</html>
