package org.hahunavth.springmvc2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany
    private List<Skill> skills;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, List<Skill> skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.skills = skills;
    }

    public boolean hasSkill(Skill skill) {
        return skills.contains(skill);
    }
}
