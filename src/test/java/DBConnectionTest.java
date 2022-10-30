import java.sql.*;
import java.sql.Driver;

import com.mysql.jdbc.*;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class DBConnectionTest {
    private static final String URL = "jdbc:mysql://localhost:3306/pp_1_1_3-4_jdbc_hibernate-master";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static void main(String[] args) {
        UserServiceTest usTest = new UserServiceTest();
        usTest.createUsersTable();
        usTest.saveUser();
        usTest.getAllUsers();
        usTest.removeUserById();
        usTest.cleanUsersTable();
        usTest.dropUsersTable();
    }
}
