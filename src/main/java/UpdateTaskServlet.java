import logic.Task;
import logic.TaskAgent;
import logic.User;
import logic.UserAgent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class UpdateTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/jsp/updateTaskPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("taskId"));
        String login = req.getParameter("login");

        String title = req.getParameter("title");
        String description = req.getParameter("description");

        User user = UserAgent.getUserByLogin(login);
        Task task = user.getTasks().get(id);

        User user1 = null;
        try {
            user1 = TaskAgent.updateTask(task, user, title, description);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        Map<Integer, Task> userTasks = user1.getTasks();

        req.setAttribute("user", user);
        req.setAttribute("login", login);
        req.setAttribute("tasks", userTasks);

        getServletContext().getRequestDispatcher("/jsp/myTasksPage.jsp").forward(req, resp);

    }

}
