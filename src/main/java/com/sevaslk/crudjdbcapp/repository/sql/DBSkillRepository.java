package com.sevaslk.crudjdbcapp.repository.sql;

import com.sevaslk.crudjdbcapp.model.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBSkillRepository {

    public List<Skill> getAll() throws SQLException {
        ResultSet rs = DBUtil.executeQueryApp("SELECT * FROM skills");
        List<Skill> skillList = new ArrayList<>();
        while (rs.next()) {
            skillList.add(new Skill(rs.getInt(1), rs.getString(2)));
        }
        rs.close();
        return skillList;
    }

    public Skill getById(int id) throws SQLException {
        ResultSet rs = DBUtil.executeQueryApp("SELECT * FROM skills WHERE idskills=" + id);
        Skill skill = null;
        while (rs.next()) {
            skill = new Skill(rs.getInt(1), rs.getString(2));
        }
        rs.close();
        return skill;
    }

    public void save(String name) throws SQLException {
        DBUtil.executeUpdateApp("INSERT INTO skills(name) VALUES('" + name + "')");
    }

    public void update(int id, String name) throws SQLException {
        DBUtil.executeUpdateApp("UPDATE skills SET name = '" + name + "' WHERE idskills=" + id);
    }

    public void deleteById(int id) throws SQLException {
        DBUtil.executeUpdateApp("DELETE FROM skills WHERE idskills=" + id);
    }
}
