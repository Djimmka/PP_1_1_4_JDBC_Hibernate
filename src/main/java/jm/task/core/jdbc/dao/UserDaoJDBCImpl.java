package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Util utilSet = Util.getInstance();
    private String INSERT = "insert into users (name, surname, age)\n" +
            "values (?,?,?);";
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Connection connect = utilSet.getConnection();
        try(Statement statement = connect.createStatement()){
            statement.execute("CREATE TABLE `pp_1_1_3-4_jdbc_hibernate-master`.`users` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `surname` VARCHAR(45) NULL,\n" +
                    "  `age` INT NULL,\n" +
                    "  PRIMARY KEY (`id`));");
            connect.close();
        } catch (SQLException e) {
            System.out.println("Таблица пользователей уже создана");
        }
    }

    public void dropUsersTable() {
        Connection connect = utilSet.getConnection();
        try(Statement statement = connect.createStatement()){
            statement.execute("drop table users;");
            connect.close();
        } catch (SQLException e) {
            System.out.println("Таблица пользователей уже удалена");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connect = utilSet.getConnection();
        PreparedStatement ps = null;
        try(PreparedStatement statement = connect.prepareStatement(INSERT)){
            statement.setInt(3, age);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.execute();
            System.out.println("Пользователь " + name + " " + lastName + " " + age + " добавлен");
        } catch (SQLException e) {
            System.out.println("Пользователь не добавлен");
        }
    }

    public void removeUserById(long id) {
        Connection connect = utilSet.getConnection();
        try(Statement statement = connect.createStatement()){
            statement.execute("delete from users where id=" + id + ";");
            connect.close();
        } catch (SQLException e) {
            System.out.println("Таблица уже пуста");
        }
    }

    public List<User> getAllUsers() {
        Connection connect = utilSet.getConnection();
        try(Statement statement = connect.createStatement()){
            List<User> res = new ArrayList<>();
            ResultSet result = statement.executeQuery("select * from users;");
            int i=0;
            while (result.next()) {
                res.add(i++, new User(result.getString(2), result.getString(3), result.getByte(4)));
            }
            System.out.println("Данные получены");
            connect.close();
            return res;
        } catch (SQLException e) {
            System.out.println("Данные не получены");
        }

        return null;
    }

    public void cleanUsersTable() {
        Connection connect = utilSet.getConnection();
        try(Statement statement = connect.createStatement()){
            statement.execute("delete from users where id>0;");
            connect.close();
        } catch (SQLException e) {
            System.out.println("Таблица уже пуста");
        }
    }
}
