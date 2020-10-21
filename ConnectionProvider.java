import java.sql.DriverManager;
import java.sql.Connection;


public class ConnectionProvider
{
    public static Connection con;
    
    public static Connection getConn() {
        if (ConnectionProvider.con == null) {
            try {
                final String property = System.getProperty("driver");
                final String property2 = System.getProperty("url");
                final String property3 = System.getProperty("user");
                final String property4 = System.getProperty("pass");
                Class.forName(property);
                ConnectionProvider.con = DriverManager.getConnection(property2, property3, property4);
            }
            catch (Exception ex) {}
        }
        return ConnectionProvider.con;
    }
    
    static {
        ConnectionProvider.con = null;
    }
}