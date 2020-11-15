package com.sevaslk.crudjdbcapp.view;

import com.sevaslk.crudjdbcapp.controller.DeveloperController;

public class DeveloperView {
    private DeveloperController developerController = new DeveloperController();

    public void getAll() {
        developerController.getAll();
    }

    public void getById(Integer id) {
        developerController.getById(id);
    }

    public void save(Integer id, String name) {
        developerController.save(id, name);
    }

    public void update(Integer id, String name) {
        developerController.update(id, name);
    }

    public void deleteById(Integer id) {
        developerController.deleteById(id);
    }

}
