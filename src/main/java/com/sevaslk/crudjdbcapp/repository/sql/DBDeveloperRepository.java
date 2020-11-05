package com.sevaslk.crudjdbcapp.repository.sql;

import com.sevaslk.crudjdbcapp.model.Developer;
import com.sevaslk.crudjdbcapp.model.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBDeveloperRepository {
    public List<Developer> getAll() throws SQLException {
        ResultSet rs = DBUtil.executeQueryApp("select developers.name, skills.name from developers\n" +
                "join developer_skills on developer_skills.developer_id = developers.id \n" +
                "join skills on developer_skills.skill_id = skills.idskills\n" +
                "order by developers.name;");
        List<Skill> skills = new ArrayList<>();
        Map<Integer, List<Skill>> tempMap = new HashMap<>();
        List<Developer> developerList = new ArrayList<>();
        while (rs.next()) {
            if (tempMap.get(rs.getInt(1)) != null) {
                skills.add(new Skill(rs.getInt(3), rs.getString(4)));
            }
            tempMap.put(rs.getInt(1), skills);
            Developer developer = new Developer(rs.getInt(1), rs.getString(2), skills, null);
            developerList.add(developer);
        }
        rs.close();
        return developerList;
    }

    public Developer getById(int id) throws SQLException {
        ResultSet rs = DBUtil.executeQueryApp("SELECT * FROM developers WHERE id = " + id);
        Developer developer = null;
        while (rs.next()) {
            developer = new Developer(rs.getInt(1), rs.getString(2), null, null);
        }
        rs.close();
        return developer;
    }

    public void save(String name) throws SQLException {
        DBUtil.executeUpdateApp("INSERT INTO developers(name) VALUES('" + name + "')");
    }

    public void update(int id, String name) throws SQLException {
        DBUtil.executeUpdateApp("UPDATE developers SET name = '" + name + "' WHERE developers.id = " + id);
    }

    public void deleteById(int id) throws SQLException {
        DBUtil.executeUpdateApp("DELETE FROM developers WHERE developers.id = " + id);
    }
}
