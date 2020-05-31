import logic.ConnectionDataBase;
import logic.Task;
import logic.User;
import logic.UserAgent;

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
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();

        users = new ConcurrentHashMap<Integer, User>();

        Connection connection = ConnectionDataBase.createConnection();

        Map<Integer, User> mapFromBD = ConnectionDataBase.getUsersFromDb(connection);

        if(mapFromBD.size() > 0) {
            for (Map.Entry<Integer, User> user : mapFromBD.entrySet()) {
                users.put(user.getKey(), user.getValue());
            }
        }

        sc.setAttribute("users", users);
        UserAgent.setUsers(users);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
