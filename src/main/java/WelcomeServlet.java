import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();

        pw.println("<html>");
        pw.println("<head><title>Welcome</title></head><body>");
        pw.println("<input type=\"submit\" value=\"Registration\" onclick=\"window.location='/login';\" />");
        pw.println("<input type=\"submit\" value=\"Enter\" onclick=\"window.location='enter';\" />");
        pw.println("</body></html>");
        pw.println("</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
