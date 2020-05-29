import logic.Task;
import logic.TaskAgent;
import logic.User;
import logic.UserAgent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateTaskServlet extends HttpServlet {

    private AtomicInteger idCounter;

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


        User user = UserAgent.getUserByLogin((String) req.getAttribute("login"));

        Map<Integer, Task> tasksUser = user.getTasks();
        final int id = this.idCounter.getAndIncrement();
        tasksUser.put(id, task);

        // добавить как-то эту таску в список именно нужного юзера хз как

        resp.sendRedirect(req.getContextPath() + "/myTasks");
    }
}
