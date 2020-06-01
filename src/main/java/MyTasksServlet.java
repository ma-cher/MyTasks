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

//    static Map<Integer, Task> tasks = TaskAgent.getTasks();
//   static User user;


    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        User user = (User) req.getAttribute("user");

        if (user == null) {
            user = UserAgent.getUserByLogin(login);
        }
//         узнали юзера для которого сейчас будем выводить таски и которому в таски сейчас будем добавлять таски


//       достаем всю мапу всех задач в которую записано все из бд при загрузке приложения
        Map<Integer, Task> tasks = new ConcurrentHashMap<Integer, Task>();
        for (Map.Entry<Integer, Task> taskEntry : TaskAgent.getTasks().entrySet()) {
                tasks.put(taskEntry.getKey(), taskEntry.getValue());
        }

        for (Map.Entry<Integer, Task> taskEntry : tasks.entrySet()) {
            System.out.println(taskEntry.getKey() + " " + taskEntry.getValue().getTitle());
        }

//        создаем мапу для считывания в нее задач конкретного юзера
        Map<Integer, Task> userTasks = new ConcurrentHashMap<Integer, Task>();

//        если во  мапе всех задач еще вообще нет задач переходим просто на страницу создания
        if(tasks.size() == 0) {
            req.setAttribute("user", user);
            req.setAttribute("login", login);
            req.setAttribute("tasks", userTasks);
            getServletContext().getRequestDispatcher("/jsp/myTasksPage.jsp").forward(req, resp);
        }
//        иначе ищем задачи конкретного юзера
        else {
            for (Map.Entry<Integer, Task> taskEntry : tasks.entrySet()) {
                if (taskEntry.getValue().getIdUser() == user.getId()){
                    userTasks.put(taskEntry.getKey(), taskEntry.getValue());
                }
            }
            user.setTasks(userTasks);
        }
        for (Map.Entry<Integer, Task> taskEntry : userTasks.entrySet()) {
            System.out.println(taskEntry.getKey() + " " + taskEntry.getValue().getTitle());
        }
//        теперь наша главная страница знает какие задачи отображать и в какие задачи складывать добавленные
        req.setAttribute("user", user);
        req.setAttribute("login", login);
        req.setAttribute("tasks", userTasks);
        getServletContext().getRequestDispatcher("/jsp/myTasksPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

