package com.sevaslk.crudjdbcapp.repository.sql;

import java.sql.*;

public class DBUtil {

    private static final String URL = "jdbc:mysql://localhost/mybase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "ssss";

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


    static ResultSet executeQueryApp(String sql) throws SQLException {
        try {
            Statement statement = getConnection().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    static void executeUpdateApp(String sql) throws SQLException {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

}
