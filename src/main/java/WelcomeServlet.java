import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// Welcome page

public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();

        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>Welcome</title>");
        pw.println("<link rel=\"stylesheet\" href=\"css/style.css\">");

        pw.println("</head>");
        pw.println("<body>");
        pw.println("<div>");
        pw.println("<h3>Welcome to MyTasks</h3>");
        pw.println("<input type=\"submit\" value=\"Registration\" onclick=\"window.location='/login';\" /><br><br>");
        pw.println("<input type=\"submit\" value=\"Enter\" onclick=\"window.location='enter';\" />");
        pw.println("</div>");
        pw.println("</body></html>");
        pw.println("</html>");

    }

}
