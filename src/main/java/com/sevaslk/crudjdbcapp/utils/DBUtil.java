package com.sevaslk.crudjdbcapp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {


    private static Properties properties = new Properties();

    static {
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String URL = properties.getProperty("URL");
    private static final String JDBC_DRIVER = properties.getProperty("JDBC_DRIVER");
    private static final String USER = properties.getProperty("USER");
    private static String PASSWORD = properties.getProperty("PASSWORD");

    private static Connection connection;

    private static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                return connection;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static ResultSet executeQueryApp(String sql) throws SQLException {
        try {
            Statement statement = getConnection().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public static void executeUpdateApp(String sql) throws SQLException {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

}
