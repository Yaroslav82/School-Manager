package com.hillel.multi.persistent.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "homeworks")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "group_name")
    private String group;

    @Column(name = "subject")
    private String subject;

    @Column(name = "deadline")
    private String deadline;

    @ManyToMany(mappedBy = "homeworks")
    private Set<Student> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return Objects.equals(name, homework.name) && Objects.equals(description, homework.description) && Objects.equals(group, homework.group) && Objects.equals(subject, homework.subject) && Objects.equals(deadline, homework.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, group, subject, deadline);
    }
}
