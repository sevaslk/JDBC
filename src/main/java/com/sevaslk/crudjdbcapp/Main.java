package com.sevaslk.crudjdbcapp;

import com.sevaslk.crudjdbcapp.repository.sql.DBAccountRepository;
import com.sevaslk.crudjdbcapp.repository.sql.DBDeveloperRepository;
import com.sevaslk.crudjdbcapp.repository.sql.DBSkillRepository;
import com.sevaslk.crudjdbcapp.repository.sql.DBUtil;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DBSkillRepository dbSkillRepository = new DBSkillRepository();
        DBAccountRepository dbAccountRepository = new DBAccountRepository();
        DBDeveloperRepository dbDeveloperRepository = new DBDeveloperRepository();

        System.out.println(dbDeveloperRepository.getAll());
//        System.out.println(dbDeveloperRepository.getById(1));
//        dbDeveloperRepository.save("Joe");
//        dbDeveloperRepository.update(22, "Max");
//        dbDeveloperRepository.deleteById(22);
//        System.out.println(dbAccountRepository.getAll());
//        System.out.println(DBUtil.executeQueryApp("SELECT * FROM skills"));
    }
}
