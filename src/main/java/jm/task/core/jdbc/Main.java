package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("Stas", "Kosyy", new Byte("21"));
        usi.saveUser("Nikita", "Boloto", new Byte("22"));
        usi.saveUser("Kostya", "Mel", new Byte("23"));
        usi.saveUser("Masha", "Trofimova", new Byte("24"));
        ArrayList<User> users = (ArrayList<User>) usi.getAllUsers();
        for (User user: users) {
            System.out.println(user);
        }
        usi.removeUserById(1);
        users = (ArrayList<User>) usi.getAllUsers();
        for (User user: users) {
            System.out.println(user);
        }
        usi.cleanUsersTable();
        System.out.println("После очистки:");
        users = (ArrayList<User>) usi.getAllUsers();
        for (User user: users) {
            System.out.println(user);
        }
        usi.dropUsersTable();
    }
}
