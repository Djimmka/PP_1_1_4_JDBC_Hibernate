package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.dao.*;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserDaoHibernateImpl uDAOH = new UserDaoHibernateImpl();

    public void createUsersTable() {
        uDAOH.createUsersTable();
    }

    public void dropUsersTable() {
        uDAOH.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        uDAOH.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        uDAOH.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return uDAOH.getAllUsers();
    }

    public void cleanUsersTable() {
        uDAOH.cleanUsersTable();
    }
}
