import logic.ConnectionDataBase;
import logic.User;
import logic.UserAgent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class EnterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/jsp/enter.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = resp.getWriter();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user;

        if (UserAgent.loginIsExist(login)) {
            user = UserAgent.getUserByLogin(login);
            if (UserAgent.checkPassword(user, password)) {
                resp.sendRedirect("/myTasks?login=" + login);
            } else {
                getServletContext().getRequestDispatcher("/jsp/wrongPassword.jsp").forward(req, resp);
            }
        } else {
            getServletContext().getRequestDispatcher("/jsp/loginNotExist.jsp").forward(req, resp);
        }
    }
}
