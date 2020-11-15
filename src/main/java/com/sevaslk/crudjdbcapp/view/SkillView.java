package com.sevaslk.crudjdbcapp.view;

import com.sevaslk.crudjdbcapp.controller.SkillController;

public class SkillView {
    private SkillController skillController = new SkillController();

    public void getAll() {
        skillController.getAll();
    }

    public void getById(Integer id) {
        skillController.getById(id);
    }

    public void save(String name) {
        skillController.save(name);
    }

    public void update(Integer id, String name) {
        skillController.update(id, name);
    }

    public void deleteById(Integer id) {
        skillController.deleteById(id);
    }

}
