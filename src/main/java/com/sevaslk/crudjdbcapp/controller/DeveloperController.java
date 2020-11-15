package com.sevaslk.crudjdbcapp.controller;

import com.sevaslk.crudjdbcapp.model.Developer;
import com.sevaslk.crudjdbcapp.repository.DeveloperRepository;
import com.sevaslk.crudjdbcapp.repository.jdbc.JDBCDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {
    private DeveloperRepository developerRepository = new JDBCDeveloperRepositoryImpl();

    public List<Developer> getAll() {
        return developerRepository.getAll();
    }

    public Developer getById(Integer id) {
        return developerRepository.getById(id);
    }

    public Developer save(Integer id, String name) {
        return developerRepository.save(new Developer(id, name, null, null));
    }

    public Developer update(Integer id, String name) {
        return developerRepository.update(new Developer(id, name, null, null));
    }

    public void deleteById(Integer id) {
        developerRepository.deleteById(id);
    }

}
