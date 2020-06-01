import logic.Task;
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

    static Map<Integer, Task> tasks = new ConcurrentHashMap<Integer, Task>();
    static User user;


    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        User user = UserAgent.getUserByLogin(login);

        Map<Integer, Task> tasks = user.getTasks();

        if(tasks.size() > 0) {
            req.setAttribute("tasks", tasks);
        }
        req.setAttribute("user", user);
        req.setAttribute("login", login);

        getServletContext().getRequestDispatcher("/jsp/myTasksPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}






//          проверка работы метода гет
//        PrintWriter pw = resp.getWriter();
//
//        String login = req.getParameter("login");
//
//        req.setAttribute("login", login);
//
//        pw.println("Hello, " + login + ", this is your task manager");
//
//        pw.println("Size of users in db = " + UserAgent.getUsers().size());
//
//        for (int i = 1; i < UserAgent.getUsers().size()+1; i++) {
//            pw.println(UserAgent.getUsers().get(i).getName());
//        }
//
//        Map<Integer, User> map = (ConcurrentHashMap<Integer, User>) getServletContext().getAttribute("users");
//
//        for (int i = 1; i < map.size()+1; i++) {
//            pw.println(map.get(i).getName());
//        }
