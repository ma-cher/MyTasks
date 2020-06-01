<%@ page import="logic.User" %>
<%@ page import="logic.Task" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.concurrent.ConcurrentHashMap" %>
<%--
  Created by IntelliJ IDEA.
  User: M.Chernyavskaya
  Date: 27.05.2020
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyTasks</title>
</head>
<body>
<h3>Create a new Task</h3><br />
<% String login = (String) request.getAttribute("login"); %>
<% User user = (User) request.getAttribute("user"); %>
<% Map<Integer, Task> tasks = (ConcurrentHashMap<Integer, Task>) request.getAttribute("tasks"); %>
<form method="post" action="/create_task">

    <label>Title</label><br>
    <input name="title"/><br>

    <label>Description</label><br>
    <input name="description"/><br>
    <input type="submit" value="Create"/>

    <% out.println("<input type=\"hidden\" name=\"login\" value=\""  + login + "\">"); %>
<%--    <% request.setAttribute("login", login);%>--%>
</form>

<div>
    <% for (Map.Entry<Integer, Task> taskEntry : tasks.entrySet()) {
        out.println("<p>Заголовок задачи</p>");
        out.println("<p>" + taskEntry.getValue().getTitle() + "</p>");
        out.println("<p>Описание задачи</p>");
        out.println("<p>" + taskEntry.getValue().getDescription() + "</p>");
    }
    %>
</div>


</body>
</html>
