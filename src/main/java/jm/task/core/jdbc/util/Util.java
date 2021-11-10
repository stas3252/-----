package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static final String URL = "jdbc:mysql://localhost:3306/mysql";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "subretjokerzona173873b";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    public static Connection connection;
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Соединение с БД прошло успешно!");
        } catch (SQLException e) {
            System.out.println("Не удалось получить соединение с БД!");
            e.printStackTrace();
        }
    }

}
