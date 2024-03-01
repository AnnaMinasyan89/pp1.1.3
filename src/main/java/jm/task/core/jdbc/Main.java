package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.postgresql.Driver;

import java.sql.*;

public class Main {

    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {

        userService.createUsersTable();

        userService.saveUser("Ани", "Погосян", (byte) 20);
        userService.saveUser("Карине", "Давтян", (byte) 14);
        userService.saveUser("Саргис", "Минасян", (byte) 34);
        userService.saveUser("Гагик", "Мкртчян", (byte) 25);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();



    }
    }

