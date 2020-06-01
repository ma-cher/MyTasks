import logic.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class ContextListener implements ServletContextListener {

    private Map <Integer, User> users;
    private Map <Integer, Task> tasks;

    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();

//        инициализируем всех пользователей при запуске приложения (скачиваем информацию с бд)
        users = new ConcurrentHashMap<Integer, User>();

        Connection connection1 = ConnectionDataBase.createConnection();

        Map<Integer, User> mapUsersFromBD = ConnectionDataBase.getUsersFromDb(connection1);
        AtomicInteger count = new AtomicInteger(0);

        if(mapUsersFromBD.size() > 0) {
            for (Map.Entry<Integer, User> user : mapUsersFromBD.entrySet()) {
                int id = count.incrementAndGet();
                user.getValue().setId(id);
                users.put(id, user.getValue());
            }
        }

        sc.setAttribute("users", users);
        UserAgent.setUsers(users);

//        инициализируем все задачи при запуске приложения (скачиваем информацию с бд) с ключом по id

        tasks = new ConcurrentHashMap<Integer, Task>();
        Connection connection2 = ConnectionDataBase.createConnection();
        Map<Integer, Task> mapTasksFromBD = ConnectionDataBase.getAllTasksFromDB(connection2);

        if (mapTasksFromBD.size() > 0) {
            for (Map.Entry<Integer, Task> task : mapTasksFromBD.entrySet()) {
                tasks.put(task.getValue().getId(), task.getValue());
            }
        }
        sc.setAttribute("allTasks", tasks);
        TaskAgent.setTasks(tasks);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
