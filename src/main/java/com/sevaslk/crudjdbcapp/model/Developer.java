package com.sevaslk.crudjdbcapp.model;

import java.util.List;
import java.util.Objects;

public class Developer {
    private Long id;
    private String name;
    private List<Skill> skills;
    private Account account;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

        public Developer(Long id, String name, List<Skill> skills, Account account) {
        this.id = id;
        this.name = name;
        this.skills = skills;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return Objects.equals(id, developer.id) &&
                Objects.equals(name, developer.name) &&
                Objects.equals(skills, developer.skills) &&
                Objects.equals(account, developer.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, skills, account);
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", skills=" + skills +
                ", account=" + account +
                '}';
    }
}
