package com.sevaslk.crudjdbcapp.repository.jdbc;

import com.sevaslk.crudjdbcapp.model.Skill;
import com.sevaslk.crudjdbcapp.repository.SkillRepository;
import com.sevaslk.crudjdbcapp.utils.DBUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCSkillRepositoryImpl implements SkillRepository {

    private static Properties properties = new Properties();

    static {
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String SELECT_ALL_SKILLS_QUERY = properties.getProperty("SELECT_ALL_SKILLS_QUERY");
    private static final String SELECT_SKILL_BY_ID_QUERY = properties.getProperty("SELECT_SKILL_BY_ID_QUERY");
    private static final String SELECT_SKILL_BY_NAME_QUERY = properties.getProperty("SELECT_SKILL_BY_NAME_QUERY");
    private static final String SAVE_SKILL_QUERY = properties.getProperty("SAVE_SKILL_QUERY");
    private static final String UPDATE_SKILL_QUERY = properties.getProperty("UPDATE_SKILL_QUERY");
    private static final String DELETE_SKILL_BY_ID_QUERY = properties.getProperty("DELETE_SKILL_BY_ID_QUERY");


    @Override
    public List<Skill> getAll() {
        List<Skill> skillList = new ArrayList<>();
        try (ResultSet rs = DBUtil.executeQueryApp(SELECT_ALL_SKILLS_QUERY)) {
            while (rs.next()) {
                Skill skill = new Skill(rs.getInt(1), rs.getString(2));
                skillList.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skillList;
    }

    @Override
    public Skill getById(Integer id) {
        try (ResultSet rs = DBUtil.executeQueryApp(String.format(SELECT_SKILL_BY_ID_QUERY, id))) {
            if (rs.next()) {
                return new Skill(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Skill();
    }

    @Override
    //field 'id' ignored for argument. skill.id autoincremented in DB and obtained using getByName()
    public Skill save(Skill skill) {
        try {
            DBUtil.executeUpdateApp(String.format(SAVE_SKILL_QUERY, skill.getName()));
            return new Skill(getByName(skill).getId(), getByName(skill).getName());
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return new Skill();
    }

    @Override
    public Skill update(Skill skill) {
        try {
            DBUtil.executeUpdateApp(String.format(UPDATE_SKILL_QUERY, skill.getName(), skill.getId()));
            return skill;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Skill();
    }

    @Override
    public void deleteById(Integer id) {
        try {
            DBUtil.executeUpdateApp(String.format(DELETE_SKILL_BY_ID_QUERY, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Skill getByName(Skill skill) {
        Skill newSkill = null;
        try (ResultSet rs = DBUtil.executeQueryApp(String.format(SELECT_SKILL_BY_NAME_QUERY, skill.getName()))) {
            while (rs.next()) {
                newSkill = new Skill(rs.getInt(1), rs.getString(2));
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return newSkill;
    }

}



