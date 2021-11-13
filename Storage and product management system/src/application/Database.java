package application;



import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
public static Connection dbConn;
public static Connection getCnxn() {

final String dbUrl = "jdbc:mysql://192.168.8.150:3306/testivarasto";
final String dbUsername = "remuser";
final String dbPassword = "passu123";

try{
    dbConn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
} catch (Exception e) {
    e.printStackTrace();
}

return dbConn;
}
} // Class