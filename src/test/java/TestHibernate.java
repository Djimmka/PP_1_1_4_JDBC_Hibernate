import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.Properties;

import static javax.persistence.GenerationType.IDENTITY;

public class TestHibernate {

    public static void main(String arg[]) {
        Properties prop= new Properties();
        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/pp_1_1_3-4_jdbc_hibernate-master");
        prop.setProperty("dialect", "org.hibernate.dialect.MySQL");
        prop.setProperty("hibernate.connection.username", "root");
        prop.setProperty("hibernate.connection.password", "root");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        prop.setProperty("hibernate.default_schema","users");
        SessionFactory sessionFactory = new Configuration().addProperties(prop).addAnnotatedClass(User.class).buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.createSQLQuery("drop table if exists users;").executeUpdate();
        }catch (RuntimeException e) {
            System.out.println("already dropped");
        }
        session.getTransaction().commit();

        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE if not exists `pp_1_1_3-4_jdbc_hibernate-master`.`users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `lastName` VARCHAR(45) NULL,\n" +
                "  `age` INT NULL,\n" +
                "   PRIMARY KEY (`id`));").executeUpdate();
        session.getTransaction().commit();

        session.beginTransaction();
        User user = new User();
        user.setName("test");
        user.setLastName("tester");
        user.setAge((byte) 2);
        session.saveOrUpdate(user);
        session.getTransaction().commit();

        session.beginTransaction();
        User user1 = session.get(User.class, (long)1);
        session.getTransaction().commit();

        System.out.println(user1);
        session.beginTransaction();
        session.delete(user1);
        session.getTransaction().commit();
        session.close();
    }

}
