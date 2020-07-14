<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<div>
<h3>Create a new Task</h3><br />
<% String login = (String) request.getAttribute("login"); %>
<% User user = (User) request.getAttribute("user"); %>
<% Map<Integer, Task> tasks = (ConcurrentHashMap<Integer, Task>) request.getAttribute("tasks"); %>
<form class="inputText" method="post" action="/create_task">

    <label>Title</label><br>
    <input name="title"/><br>

    <label>Description</label><br>
    <input name="description"/><br><br>
    <input type="submit" value="Create"/>

    <% out.println("<input type=\"hidden\" name=\"login\" value=\""  + login + "\">"); %>
<%--    <% request.setAttribute("login", login);%>--%>
</form>
</div><br>

    <% for (Map.Entry<Integer, Task> taskEntry : tasks.entrySet()) {
        out.println("<div>");
        out.println("<p class=\"title\">" + taskEntry.getValue().getTitle() + "</p>");
        out.println("<p>" + taskEntry.getValue().getDescription() + "</p>");
        out.println("<form class=\"inputText\" method=\"post\" action=\"/update_task\">");
        out.println("<input type=\"submit\" value=\"Update\" onclick=\"window.location='/update_task'>");
        out.println("<input type=\"hidden\" name=\"taskId\" value=\""  + taskEntry.getKey() + "\">");
        out.println("<input type=\"hidden\" name=\"login\" value=\""  + login + "\">");
        out.println(" </form>");
        out.println("<form class=\"inputText\" method=\"post\" action=\"/delete_task\">");
        out.println("<input type=\"submit\" value=\"Delete\"/>");
        out.println("<input type=\"hidden\" name=\"taskId\" value=\""  + taskEntry.getKey() + "\">");
        out.println("<input type=\"hidden\" name=\"login\" value=\""  + login + "\">");
        out.println(" </form>");
        out.println("</div><br>");
    }
    %>


</body>
</html>