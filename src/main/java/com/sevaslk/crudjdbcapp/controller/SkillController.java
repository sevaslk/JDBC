package com.sevaslk.crudjdbcapp.controller;

import com.sevaslk.crudjdbcapp.model.Skill;
import com.sevaslk.crudjdbcapp.repository.SkillRepository;
import com.sevaslk.crudjdbcapp.repository.jdbc.JDBCSkillRepositoryImpl;

import java.util.List;

public class SkillController {
    private SkillRepository skillRepository = new JDBCSkillRepositoryImpl();

    public List<Skill> getAll() {
        return skillRepository.getAll();
    }

    public Skill getById(Integer id) {
        return skillRepository.getById(id);
    }

    public Skill save(String name) {
        return skillRepository.save(new Skill(1, name));
    }

    public Skill update(Integer id, String name) {
        return skillRepository.update(new Skill(id, name));
    }

    public void deleteById(Integer id) {
        skillRepository.deleteById(id);
    }

}
