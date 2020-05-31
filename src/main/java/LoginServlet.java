import logic.ConnectionDataBase;
import logic.User;
import logic.UserAgent;
import sun.management.Agent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class LoginServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = resp.getWriter();

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);

     //    добавляем юзера  в мапу в классе юзерАгент

        if (UserAgent.add(user)) { // если логин занят вернет false и переведет на страницу "логин занят"

            resp.sendRedirect("/myTasks?login=" + login);

        } else {
            getServletContext().getRequestDispatcher("/jsp/loginIsBusy.jsp").forward(req, resp);
        }
    }
}






//        try {
//            if (connection != null) {
//                Statement statement = connection.createStatement();
//
//                ResultSet rs = statement.executeQuery("Select * from users;");
//                List<String> logins = new ArrayList<String>();
//
//                while (rs.next()) {
//                    logins.add(rs.getString("login"));
//                }
//                if (logins.contains(login)) {
//                    getServletContext().getRequestDispatcher("/jsp/loginIsBusy.jsp").forward(req, resp);
//                }
//                else {
//                    String sql = String.format("INSERT INTO `users` (`name`, `login`, `password`) " +
//                            "VALUES ('%s', '%s', '%s');", name, login, password);
//                    statement.executeUpdate(sql);
//                    resp.sendRedirect("/myTasks?login=" + login);
//                }
//            } else System.out.println("connection error");
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            pw.println(throwables.getMessage());
//        }

