import java.sql.*;
import java.sql.Driver;

import com.mysql.jdbc.*;
//import com.mysql.jdbc.Driver;

public class DBConnectionTest {
    private static final String URL = "jdbc:mysql://localhost:3306/pp_1_1_3-4_jdbc_hibernate-master";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static void main(String[] args) {
        Connection connection;
        try{
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Connected");
            }
            try (Statement statement = connection.createStatement()){
                statement.execute("drop table t1;");
            }
            connection.close();
            if (connection.isClosed()) {
                System.out.println("Disconnected");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
