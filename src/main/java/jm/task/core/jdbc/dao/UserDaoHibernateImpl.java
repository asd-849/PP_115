package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;

import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.connectHiber().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(45)," +
                    "lastName VARCHAR(45)," +
                    "age INT)").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.connectHiber().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.connectHiber().openSession()) {
            Transaction transaction = session.beginTransaction();
            //User user = new User(name, lastName, age);
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.printf("User с именем - %s добавлен в базу данных%n", name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.connectHiber().openSession()) {
            Transaction transaction = session.beginTransaction();
            //User user = session.get(User.class, id);
            //session.delete(user);
            session.createSQLQuery("DELETE FROM users WHERE id = " + id).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = Util.connectHiber().openSession()) {
            Transaction transaction = session.beginTransaction();
            users = session.createSQLQuery("SELECT * FROM users").addEntity(User.class).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.connectHiber().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
