package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util DB = new Util();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS USERS " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                " lastName VARCHAR (50), " +
                " age Integer NOT NULL, " +
                " PRIMARY KEY (id))";
        try {
            Statement statement = DB.connection.createStatement();
            statement.executeUpdate(SQL);
            System.out.println("Таблица USERS успешно создана!");
        } catch (SQLException e) {
            System.out.println("Таблица USERS не создана!");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Statement statement = null;
        try {
            statement = DB.connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS USERS");
            System.out.println("таблица USERS успешно удалена!");
        } catch (SQLException e) {
            System.out.println("ERR! таблица USERS не удалена!");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement statement = null;
        try {
            statement = DB.connection.prepareStatement("INSERT USERS(name, lastName, age) VALUES(?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("не удалось USER - " + name + " добавить в базу данных");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        PreparedStatement statement = null;
        try {
            statement = DB.connection.prepareStatement("DELETE FROM USERS WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User с Id = " + id + " удален из таблицы USERS!");
        } catch (SQLException e) {
            System.out.println("ERR! не удалось удалить User с Id = " + id + " из таблицы USERS!");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        ResultSet res;
        List<User> users = new ArrayList<>();
        Statement statement = null;
        try {
            statement = DB.connection.createStatement();
            res = statement.executeQuery("SELECT * FROM USERS");
            System.out.println("Все данные считаны!");

            while (res.next()) {
                long id = Integer.parseInt(res.getString(1)); // не работает res.getLong()
                String name = res.getString(2);
                String lastName = res.getString(3);
                byte age = (byte)Integer.parseInt(res.getString(4)); // не работает res.getByte()
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("ERR! не удалось считать все данные!");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        try {
            statement = DB.connection.createStatement();
            statement.executeUpdate("DELETE FROM USERS");
        } catch (SQLException e) {
            System.out.println("ERR! таблица USERS не очищена!");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
