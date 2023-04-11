package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/schema_1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;
    public static Connection connect() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
}
