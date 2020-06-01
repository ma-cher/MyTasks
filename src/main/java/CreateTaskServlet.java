import logic.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateTaskServlet extends HttpServlet {

    private AtomicInteger idCounter = new AtomicInteger(0);

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        String description = req.getParameter("description");

        Task task = new Task();

        task.setTitle(title);
        task.setDescription(description);

        String login = req.getParameter("login");
        User user = UserAgent.getUserByLogin(login);
        System.out.println(user.toString());

        Map<Integer, Task> tasksUser = new ConcurrentHashMap<Integer, Task>();
        int id = user.getTasks().size();

        if (id > 0) {
         tasksUser = user.getTasks();
        }

        if (tasksUser.size() == 0) {
            tasksUser.put(1, task);
            user.setTasks(tasksUser);
            Connection connection = ConnectionDataBase.createConnection();
            ConnectionDataBase.addTaskToDB(connection, user, task);

            req.setAttribute("user", user);
            req.setAttribute("login", login);
            resp.sendRedirect("/myTasks?login=" + login);

        } else {
            tasksUser = user.getTasks();
             int idTask = id++;
            tasksUser.put(id, task);
            user.setTasks(tasksUser);
            Connection connection = ConnectionDataBase.createConnection();
            ConnectionDataBase.addTaskToDB(connection, user, task);

            req.setAttribute("user", user);
            req.setAttribute("login", login);
            resp.sendRedirect("/myTasks?login=" + login);
        }

    }
}
