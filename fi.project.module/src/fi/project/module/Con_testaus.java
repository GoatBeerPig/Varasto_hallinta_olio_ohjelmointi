package fi.project.module;



import java.sql.*;

public class Con_testaus {
   public static void main(String[] args ) throws Exception {
      // register Oracle thin driver with DriverManager service
      // it is optional for JDBC4.x version
      // Class.forName("com.mysql.cj.jdbc.Driver");
    
      // declare variables
      // place your own values
      final String url = "jdbc:mysql://192.168.8.145:3306/testivarasto";
      final String user = "remuser";
      final String password = "passu123";

      // establish the connection
      Connection con = DriverManager.getConnection(url, user, password);

      // display status message
      if(con == null) {
         System.out.println("JDBC connection is not established");
         return;
      }
      else 
         System.out.println("Congratulations,"+
            " JDBC connection is established successfully.\n");
      // close JDBC connection
      con.close();
   } //main
} //class


