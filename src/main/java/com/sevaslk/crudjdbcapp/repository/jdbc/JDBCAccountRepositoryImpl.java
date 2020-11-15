package com.sevaslk.crudjdbcapp.repository.jdbc;

import com.sevaslk.crudjdbcapp.model.Account;
import com.sevaslk.crudjdbcapp.model.AccountStatus;
import com.sevaslk.crudjdbcapp.repository.AccountRepository;
import com.sevaslk.crudjdbcapp.utils.DBUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCAccountRepositoryImpl implements AccountRepository {

    private static Properties properties = new Properties();

    static {
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String SELECT_ALL_ACCOUNTS_QUERY = properties.getProperty("SELECT_ALL_ACCOUNTS_QUERY");
    private static final String SELECT_ACCOUNT_BY_ID_QUERY = properties.getProperty("SELECT_ACCOUNT_BY_ID_QUERY");
    private static final String SAVE_ACCOUNT_QUERY = properties.getProperty("SAVE_ACCOUNT_QUERY");
    private static final String UPDATE_ACCOUNT_QUERY = properties.getProperty("UPDATE_ACCOUNT_QUERY");
    private static final String DELETE_ACCOUNT_BY_ID_QUERY = properties.getProperty("DELETE_ACCOUNT_BY_ID_QUERY");

    public List<Account> getAll() {
        List<Account> accountList = new ArrayList<>();
        try (ResultSet rs = DBUtil.executeQueryApp(SELECT_ALL_ACCOUNTS_QUERY)) {
            while (rs.next()) {
                Account account = new Account(rs.getInt(1), AccountStatus.valueOf(rs.getString(2)));
                accountList.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    public Account getById(Integer id) {
        try (ResultSet rs = DBUtil.executeQueryApp(String.format(SELECT_ACCOUNT_BY_ID_QUERY, id))) {
            if (rs.next()) {
                return new Account(rs.getInt(1), AccountStatus.valueOf(rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account save(Account account) {
        try {
            DBUtil.executeUpdateApp(String.format(SAVE_ACCOUNT_QUERY, account.getId(), account.getAccountStatus()));
            return account;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account update(Account account) {
        try {
            DBUtil.executeUpdateApp(String.format(UPDATE_ACCOUNT_QUERY, account.getAccountStatus(), account.getId()));
            return account;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteById(Integer id) {
        try {
            DBUtil.executeUpdateApp(String.format(DELETE_ACCOUNT_BY_ID_QUERY, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
