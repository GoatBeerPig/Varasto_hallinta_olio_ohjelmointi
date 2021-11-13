package fi.project.module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Varasto {
   public static void main(String[] args) throws Exception {
      // variables
             final String url = "jdbc:mysql://192.168.8.145:3306/testivarasto";
             final String user = "remuser";
             final String password = "passu123"; 
             
             String sql_drop = "DROP TABLE toinen_varasto";

             

      // establish the connection
      Connection con = DriverManager.getConnection(url, user, password);

      // create JDBC statement object
      Statement st = con.createStatement();
      
      
      st.executeUpdate(sql_drop);
      st.execute("create table toinen_varasto (id integer, name varchar(20))");                        ///tämmönen varmaan tarvii jonku iffin, joka checkaa onko table jo olemassa
      st.execute("insert into toinen_varasto values(1,'tuote2')");


      // prepare SQL query
      String query = "SELECT id, NAME FROM varasto";

      // send and execute SQL query in Database software
      ResultSet rs2 = st.executeQuery("select * from toinen_varasto");
      
      if (rs2 != null)  
          while (rs2.next()) 
      {  
          System.out.println("Tuotteen Id on: " + rs2.getInt(1));  
          System.out.println("Tuotteen nimi on: " + rs2.getString(2));  
      }  
      rs2.close();

      // process the ResultSet object
      boolean flag = false;
      
      ResultSet rs = st.executeQuery(query);

      while (rs.next()) {
         flag = true;
         System.out.println("'" + rs.getInt(1) +
                           "', '" + rs.getString(2) + "'");
      }

      if (flag == true) {
         System.out.println("\nRecords retrieved and displayed");
      } else {
         System.out.println("Record not found");
      }
      
      
      
      

      // close JDBC objects
      rs.close();
      st.close();
      con.close();
   }
}