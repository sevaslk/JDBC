package com.sevaslk.crudjdbcapp.controller;

import com.sevaslk.crudjdbcapp.model.Account;
import com.sevaslk.crudjdbcapp.model.AccountStatus;
import com.sevaslk.crudjdbcapp.repository.AccountRepository;
import com.sevaslk.crudjdbcapp.repository.jdbc.JDBCAccountRepositoryImpl;

import java.util.List;

public class AccountController {
    private AccountRepository accountRepository = new JDBCAccountRepositoryImpl();

    public List<Account> getAll() {
        return accountRepository.getAll();
    }

    public Account getById(Integer id) {
        return accountRepository.getById(id);
    }

    public Account save(Integer id) {
        return accountRepository.save(new Account(id, null));
    }

    public Account update(Integer id, String name) {
        return accountRepository.update(new Account(id, AccountStatus.valueOf(name)));
    }

    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }
}
