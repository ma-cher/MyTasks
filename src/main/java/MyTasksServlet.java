import logic.User;
import logic.UserAgent;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyTasksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter pw = resp.getWriter();

        String login = req.getParameter("login");

        req.setAttribute("login", login);

        pw.println("Hello, " + login + ", this is your task manager");

        pw.println("Size of users in db = " + UserAgent.getUsers().size());

        for (int i = 1; i < UserAgent.getUsers().size()+1; i++) {
            pw.println(UserAgent.getUsers().get(i).getName());
        }

        Map<Integer, User> map =(ConcurrentHashMap<Integer, User>) getServletContext().getAttribute("users");

        for (int i = 1; i < map.size()+1; i++) {
            pw.println(map.get(i).getName());
        }


    }

}
