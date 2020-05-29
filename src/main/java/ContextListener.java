import logic.Task;
import logic.User;
import logic.UserAgent;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class ContextListener implements ServletContextListener {

//    private Map <Integer, User> users;
//    private AtomicInteger id;

    public void contextInitialized(ServletContextEvent event) {
//        ServletContext sc = event.getServletContext();
//
//        users = new ConcurrentHashMap<Integer, User>();
//        id = new AtomicInteger(0);
//        sc.setAttribute("users", users);
//        Map<Integer, User> mapFromUserAgent = UserAgent.getUsers();
//
//        for (Map.Entry<Integer, User> user : mapFromUserAgent.entrySet()) {
//            users.put(user.getKey(), user.getValue());
//        }

    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
