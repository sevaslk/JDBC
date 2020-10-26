package com.sevaslk.crudjdbcapp.repository.sql;

import java.sql.*;

class DBUtil {

    private static final String URL = "jdbc:mysql://localhost/mybase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "ssss";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;


    static ResultSet executeQueryApp(String sql) throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException(e);
        } finally {
//            connection.close();
//            statement.close();
//            resultSet.close();
        }
    }

    static void executeUpdateApp(String sql) throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException(e);
        } finally {
            connection.close();
            statement.close();
        }
    }

}
