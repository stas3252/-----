package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("Stas", "Kosyy", (byte)20);
        usi.saveUser("Nikita", "Boloto", (byte)21);
        usi.saveUser("Kostya", "Mel", (byte)22);
        usi.saveUser("Masha", "Trofimova", (byte)23);
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
