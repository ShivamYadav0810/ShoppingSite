import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.StringTokenizer;

public class TableCreate {
   public static void createTab(String var0) {
      try {
         FileInputStream var1 = new FileInputStream(var0);
         byte[] var2 = new byte[var1.available()];
         var1.read(var2);
         var1.close();
         String var3 = new String(var2);
         StringTokenizer var4 = new StringTokenizer(var3, "/");
         Connection var5 = ConnectionProvider.getConn();
         Statement var6 = var5.createStatement();

         while(var4.hasMoreTokens()) {
            String var7 = var4.nextToken();
            if (var7.trim().equals("stop")) {
               break;
            }

            var6.executeUpdate(var7);
            System.out.println(var7);
         }
      } catch (Exception var8) {
         System.out.println(var8);
      }

   }
}