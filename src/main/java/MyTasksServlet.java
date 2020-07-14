import logic.Task;
import logic.TaskAgent;
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
        resp.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");

//        get user from create task page. if its the first time - get user by login

        User user = (User) req.getAttribute("user");

        if (user == null) {
            user = UserAgent.getUserByLogin(login);
        }

//        get map of all tasks


        Map<Integer, Task> tasks = new ConcurrentHashMap<Integer, Task>();
        for (Map.Entry<Integer, Task> taskEntry : TaskAgent.getTasks().entrySet()) {
            tasks.put(taskEntry.getKey(), taskEntry.getValue());
        }

//        create the map to get all tasks of user

        Map<Integer, Task> userTasks = new ConcurrentHashMap<Integer, Task>();

//       if there isn't any task go to main page again

        if (tasks.size() == 0) {
            req.setAttribute("user", user);
            req.setAttribute("login", login);
            req.setAttribute("tasks", userTasks);
            getServletContext().getRequestDispatcher("/jsp/myTasksPage.jsp").forward(req, resp);
        }

//        or find the tasks of user

        else {
            for (Map.Entry<Integer, Task> taskEntry : tasks.entrySet()) {
                if (taskEntry.getValue().getIdUser() == user.getId()) {
                    userTasks.put(taskEntry.getKey(), taskEntry.getValue());
                }
            }
            user.setTasks(userTasks);
        }
        for (Map.Entry<Integer, Task> taskEntry : userTasks.entrySet()) {
            System.out.println(taskEntry.getKey() + " " + taskEntry.getValue().getTitle());
        }
//        now our main page get all tasks to view and where to put others
        req.setAttribute("user", user);
        req.setAttribute("login", login);
        req.setAttribute("tasks", userTasks);
        getServletContext().getRequestDispatcher("/jsp/myTasksPage.jsp").forward(req, resp);
    }
}

