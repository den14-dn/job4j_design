package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;

public class ConnectionDemo {
    private static Connection getConnection() throws Exception {
        Config config = new Config("./data/app.properties");
        config.load();

        Class.forName(config.value("driver_class"));
        String url = config.value("url");
        String login = config.value("username");
        String password = config.value("password");
        return DriverManager.getConnection(url, login, password);
    }

    public static void main(String[] args) throws Exception {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
