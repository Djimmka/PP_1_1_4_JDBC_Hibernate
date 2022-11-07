package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = Util.getInstance().getSessionFactory();
    private Transaction tr = null;

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE if not exists `pp_1_1_3-4_jdbc_hibernate-master`.`users` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `lastName` VARCHAR(45) NULL,\n" +
                    "  `age` INT NULL,\n" +
                    "  PRIMARY KEY (`id`));").executeUpdate();
            tr.commit();
        } catch (TransactionException e) {
            System.out.println("Already created");
            tr.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            session.createSQLQuery("drop table if exists users;").executeUpdate();
            tr.commit();
        } catch (TransactionException e) {
            System.out.println("Already deleted");
            tr.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            User user = new User(name, lastName, age);
            tr = session.beginTransaction();
            session.save(user);
            tr.commit();
        } catch (TransactionException e) {
            System.out.println("Already exist");
            tr.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            tr.commit();
        } catch (TransactionException e) {
            System.out.println("Already removed");
            tr.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> userCriteriaQuery = builder.createQuery(User.class);
            userCriteriaQuery.from(User.class);
            users = session.createQuery(userCriteriaQuery).getResultList();
            tr.commit();
        } catch (TransactionException e) {
            System.out.println("No users");
            tr.rollback();
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            tr = session.beginTransaction();
            session.createSQLQuery("truncate table users;").executeUpdate();
            tr.commit();
        } catch (TransactionException e) {
            System.out.println("Already deleted");
            if (tr!=null) {
                tr.rollback();
            }
            e.printStackTrace();
        }
    }
}
