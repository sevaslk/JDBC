package com.sevaslk.crudjdbcapp;

import com.sevaslk.crudjdbcapp.repository.sql.DBSkillRepository;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DBSkillRepository dbSkillRepository = new DBSkillRepository();

//        System.out.println(dbSkillRepository.getAll());
//        System.out.println(dbSkillRepository.getById(1));
//        dbSkillRepository.save("C++");
//        dbSkillRepository.update(4, "Java");
//        dbSkillRepository.deleteById(2);
        System.out.println(dbSkillRepository.getAll());

    }
}
