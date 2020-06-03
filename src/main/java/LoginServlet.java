import logic.ConnectionDataBase;
import logic.User;
import logic.UserAgent;
import sun.management.Agent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//  registration

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = resp.getWriter();

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);

     //    add user in UserAgent class to map and db

        if (UserAgent.add(user)) { // if login is busy redirect to login is busy page
            resp.sendRedirect("/myTasks?login=" + login);
        } else {
            getServletContext().getRequestDispatcher("/jsp/loginIsBusy.jsp").forward(req, resp);
        }
    }
}


