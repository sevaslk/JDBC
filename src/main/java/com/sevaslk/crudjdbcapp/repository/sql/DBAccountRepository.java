package com.sevaslk.crudjdbcapp.repository.sql;

import com.sevaslk.crudjdbcapp.model.Account;
import com.sevaslk.crudjdbcapp.model.AccountStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBAccountRepository {

    public List<Account> getAll() throws SQLException {
        ResultSet rs = DBUtil.executeQueryApp("SELECT * FROM accounts");
        List<Account> accountList = new ArrayList<>();
        while (rs.next()) {
            Account account = new Account(rs.getInt(1), AccountStatus.valueOf(rs.getString(2)));
            accountList.add(account);
        }
        rs.close();
        return accountList;
    }

    public Account getById(int id) throws SQLException {
        ResultSet rs = DBUtil.executeQueryApp("SELECT * FROM accounts WHERE idaccounts =" + id);
        Account account = null;
        while (rs.next()) {
            account = new Account(rs.getInt(1), AccountStatus.valueOf(rs.getString(2)));
        }
        rs.close();
        return account;
    }

    public void save(int accountNumber) throws SQLException {
        DBUtil.executeUpdateApp("INSERT INTO accounts(idaccounts, account_status) VALUES('" + accountNumber + "', " + "'ACTIVE')");
    }

    public void update(int accountNumber, String accountStatus) throws SQLException {
        DBUtil.executeUpdateApp("UPDATE accounts SET account_status = '" + accountStatus + "' WHERE idaccounts=" + accountNumber);
    }

    public void deleteById(int id) throws SQLException {
        DBUtil.executeUpdateApp("DELETE FROM accounts WHERE idaccounts = " + id);
    }
}



