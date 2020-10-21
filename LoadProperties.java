import java.util.Enumeration;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;


public class LoadProperties
{
    public static void propLoad(final String name) {
        try {
            final FileInputStream inStream = new FileInputStream(name);
            final Properties properties = new Properties();
            properties.load(inStream);
            final Enumeration<?> propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                final String str = (String)propertyNames.nextElement();
                final String property = properties.getProperty(str);
                System.setProperty(str, property);
                System.out.println(str + " : " + property);
            }
        }
        catch (Exception ex) {}
    }
}