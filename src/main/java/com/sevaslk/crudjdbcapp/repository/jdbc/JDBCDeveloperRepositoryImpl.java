package com.sevaslk.crudjdbcapp.repository.jdbc;

import com.sevaslk.crudjdbcapp.model.Developer;
import com.sevaslk.crudjdbcapp.model.Skill;
import com.sevaslk.crudjdbcapp.repository.DeveloperRepository;
import com.sevaslk.crudjdbcapp.utils.DBUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JDBCDeveloperRepositoryImpl implements DeveloperRepository {

    private static Properties properties = new Properties();

    static {
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String SELECT_ALL_DEVELOPER_QUERY = properties.getProperty("SELECT_ALL_DEVELOPER_QUERY");
    private static final String SELECT_DEVELOPER_BY_ID_QUERY = properties.getProperty("SELECT_DEVELOPER_BY_ID_QUERY");
    private static final String SELECT_DEVELOPER_BY_NAME_QUERY = properties.getProperty("SELECT_DEVELOPER_BY_NAME_QUERY");
    private static final String SAVE_DEVELOPER_QUERY = properties.getProperty("SAVE_DEVELOPER_QUERY");
    private static final String UPDATE_DEVELOPER_QUERY = properties.getProperty("UPDATE_DEVELOPER_QUERY");
    private static final String DELETE_DEVELOPER_BY_ID_QUERY = properties.getProperty("DELETE_DEVELOPER_BY_ID_QUERY");

    public List<Developer> getAll() {
        final Map<Developer, List<Skill>> developerSkillsMap = new HashMap<>();
        try (ResultSet rs = DBUtil.executeQueryApp(SELECT_ALL_DEVELOPER_QUERY)) {
            while (rs.next()) {
                Developer developer = new Developer(rs.getInt(1), rs.getString(2), null, null);

                if (developerSkillsMap.get(developer) != null) {
                    Skill developerSkill = new Skill(rs.getInt(3), rs.getString(4));
                    List<Skill> updatedSkills = developerSkillsMap.get(developer);
                    updatedSkills.add(developerSkill);
                    developerSkillsMap.put(developer, updatedSkills);
                } else {
                    Skill developerSkill = new Skill(rs.getInt(3), rs.getString(4));
                    developerSkillsMap.put(developer, new ArrayList<>(Collections.singletonList(developerSkill)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Developer> developers = new ArrayList<>(developerSkillsMap.keySet());

        developers.forEach(d -> {
            d.setSkills(developerSkillsMap.get(d));
        });
        return new ArrayList<>(developerSkillsMap.keySet());
    }

    public Developer getById(Integer id) {
        Developer developer = null;
        try (ResultSet rs = DBUtil.executeQueryApp(String.format(SELECT_DEVELOPER_BY_ID_QUERY, id))) {
            while (rs.next()) {
                developer = new Developer(rs.getInt(1), rs.getString(2), null, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    public Developer save(Developer developer) {
        try {
            DBUtil.executeUpdateApp(String.format(SAVE_DEVELOPER_QUERY, developer.getName()));
            return new Developer(getByName(developer).getId(), developer.getName(), developer.getSkills(), developer.getAccount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Developer update(Developer developer) {
        try {
            DBUtil.executeUpdateApp(String.format(UPDATE_DEVELOPER_QUERY, developer.getName(), developer.getId()));
            return developer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteById(Integer id) {
        try {
            DBUtil.executeUpdateApp(String.format(DELETE_DEVELOPER_BY_ID_QUERY, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Developer getByName(Developer developer) {
        try (ResultSet rs = DBUtil.executeQueryApp(String.format(SELECT_DEVELOPER_BY_NAME_QUERY, developer.getName()))) {
            if (rs.next()) {
                return new Developer(rs.getInt(1), rs.getString(2), developer.getSkills(), developer.getAccount());
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
