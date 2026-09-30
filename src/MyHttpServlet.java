import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>HTTP Servlet</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<h2>Welcome to HTTP Servlet Example!</h2>");
        out.println("<p>This is a response from doGet() method.</p>");
        out.println("</body>");

        out.println("</html>");
    }
}
