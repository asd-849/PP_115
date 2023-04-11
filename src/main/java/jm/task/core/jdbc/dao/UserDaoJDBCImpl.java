package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.connect(); Statement query = connection.createStatement()) {
            query.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(45)," +
                    "lastName VARCHAR(45)," +
                    "age INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.connect(); Statement query = connection.createStatement()) {
            query.execute("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.connect();
             PreparedStatement query = connection.prepareStatement("INSERT INTO users values(default, ?, ?, ?)")) {
            query.setString(1, name);
            query.setString(2, lastName);
            query.setByte(3, age);
            query.execute();
            System.out.printf("User с именем - %s добавлен в базу данных%n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.connect();
             PreparedStatement query = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            query.setLong(1, id);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = Util.connect(); Statement query = connection.createStatement()) {
            ResultSet usersSet = query.executeQuery("SELECT * FROM users");
            while (usersSet.next()) {
                User user = new User();
                user.setId(usersSet.getLong("id"));
                user.setName(usersSet.getString("name"));
                user.setLastName(usersSet.getString("lastName"));
                user.setAge(usersSet.getByte("age"));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.connect(); Statement query = connection.createStatement()) {
            query.execute("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
