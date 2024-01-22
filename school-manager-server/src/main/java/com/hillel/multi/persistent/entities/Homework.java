package com.hillel.multi.persistent.entities;

import com.hillel.multi.persistent.configuration.annotations.ValidGroupName;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "group_name")
    @ValidGroupName
    private String groupName;

    @Column(name = "subject")
    @NotBlank
    private String subject;

    @Column(name = "deadline")
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$")
    @NotBlank
    private String deadline;

    @ManyToMany(mappedBy = "homeworks")
    @Valid
    private Set<Student> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return Objects.equals(name, homework.name) && Objects.equals(description, homework.description) && Objects.equals(groupName, homework.groupName) && Objects.equals(subject, homework.subject) && Objects.equals(deadline, homework.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, groupName, subject, deadline);
    }
}
