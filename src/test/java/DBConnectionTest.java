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

//        Properties prop= new Properties();
//
//        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/pp_1_1_3-4_jdbc_hibernate-master");
//
//        //You can use any database you want, I had it configured for Postgres
//        prop.setProperty("dialect", "org.hibernate.dialect.MySQL");
//
//        prop.setProperty("hibernate.connection.username", "root");
//        prop.setProperty("hibernate.connection.password", "root");
//        prop.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//        prop.setProperty("show_sql", String.valueOf(true)); //If you wish to see the generated sql query
//
//        SessionFactory sessionFactory = new Configuration().addProperties(prop).buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        User user = new User(); //Note customer is a POJO maps to the customer table in the database.
//
//        user.setName("test");
//        user.setLastName("tester");
//        user.setAge((byte) 2);
//        session.save(user);
//        session.getTransaction().commit();
//        session.close();
    }
}
