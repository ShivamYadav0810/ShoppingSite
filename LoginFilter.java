import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletContext;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;
import javax.servlet.FilterConfig;
import javax.servlet.Filter;


public class LoginFilter implements Filter
{
    FilterConfig config;
    
    public void init(final FilterConfig config) throws ServletException {
        this.config = config;
    }
    
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws ServletException, IOException {
        final String parameter = servletRequest.getParameter("userId");
        final String parameter2 = servletRequest.getParameter("pass");
        servletRequest.getParameterValues("cooky");
        final PrintWriter writer = servletResponse.getWriter();
        final ServletContext servletContext = this.config.getServletContext();
        try {
            final Statement statement = ((Connection)servletContext.getAttribute("con")).createStatement();
            final ResultSet executeQuery = statement.executeQuery("select * from cust where userId = '" + parameter + "' and pass = '" + parameter2 + "'");
            if (executeQuery.next()) {
                if (executeQuery.getString("loginStatus").equals("F")) {
                    statement.executeUpdate("update cust set loginStatus='T' where userId = '" + parameter + "' and pass = '" + parameter2 + "'");
                    filterChain.doFilter(servletRequest, servletResponse);
                    ((HttpServletResponse)servletResponse).setHeader("Refresh", "0;welcome.html");
                }
                else {
                    writer.println("<html><body><h2>User Already Logged In</h2></body></html>");
                    ((HttpServletResponse)servletResponse).setHeader("Refresh", "4;index.html");
                }
            }
            else {
                servletRequest.getRequestDispatcher("/index.html").forward(servletRequest, servletResponse);
            }
        }
        catch (Exception x) {
            writer.println(x);
        }
    }
    
    public void destroy() {
    }
}