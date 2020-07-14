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
import java.util.Iterator;
import java.util.Map;

public class DeleteTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =Integer.parseInt(req.getParameter("taskId"));
        String login = req.getParameter("login");

        User user = UserAgent.getUserByLogin(login);
        Task task = user.getTasks().get(id);

        User user1 = null;
        try {
            user1 = TaskAgent.removeTask(task, user);
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
