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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        String description = req.getParameter("description");

        Task task = new Task();

        task.setTitle(title);
        task.setDescription(description);

        String login = req.getParameter("login");
        User user = UserAgent.getUserByLogin(login);
        Task taskWithID = TaskAgent.addTask(task, user);

        Map<Integer, Task> userTasks = user.getTasks();

        userTasks.put(taskWithID.getId(), taskWithID);
        user.setTasks(userTasks);

        req.setAttribute("user", user);
        req.setAttribute("login", login);
        req.setAttribute("tasks", userTasks);

        getServletContext().getRequestDispatcher("/jsp/myTasksPage.jsp").forward(req, resp);

    }
}
