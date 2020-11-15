package com.sevaslk.crudjdbcapp.view;

import com.sevaslk.crudjdbcapp.controller.AccountController;

public class AccountView {
    private AccountController accountController = new AccountController();

    public void getAll() {
        accountController.getAll();
    }

    public void getById(Integer id) {
        accountController.getById(id);
    }

    public void save(Integer id) {
        accountController.save(id);
    }

    public void update(Integer id, String name) {
        accountController.update(id, name);
    }

    public void deleteById(Integer id) {
        accountController.deleteById(id);
    }

}
