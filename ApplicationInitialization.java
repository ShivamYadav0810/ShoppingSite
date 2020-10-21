import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;


public class ApplicationInitializer extends HttpServlet
{
    public void init(final ServletConfig servletConfig) throws ServletException {
        final ServletContext servletContext = servletConfig.getServletContext();
        final String initParameter = servletContext.getInitParameter("oracletab");
        final String realPath = servletContext.getRealPath("WEB-INF//db/db.properties");
        final String realPath2 = servletContext.getRealPath("WEB-INF//dbtable/sqltable.txt");
        LoadProperties.propLoad(realPath);
        if (initParameter.equals("yes")) {
            TableCreate.createTab(realPath2);
            System.out.println("Table Created Successfully");
        }
    }
}