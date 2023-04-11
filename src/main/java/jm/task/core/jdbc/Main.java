package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService test = new UserServiceImpl();
        test.createUsersTable();
        test.saveUser("Ivan", "Ivanov", (byte)14);
        test.saveUser("Dmitriy", "Dmitrov", (byte)42);
        test.saveUser("Alexander", "Alexandrov", (byte)31);
        test.saveUser("Ivan", "Ivanov", (byte)14);
        test.getAllUsers().stream().forEach(System.out::println);
        test.cleanUsersTable();
        test.dropUsersTable();
    }
}
