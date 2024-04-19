package com.hillel.multi.persistent.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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
    @Pattern(regexp = "^[A-Z]{2,3}-\\d+$", message = "Should be in format: \'GB-123\'")
    @NotBlank
    private String groupName;

    @Column(name = "subject")
    @NotBlank
    private String subject;

    @Column(name = "deadline")
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", message = "Should be in format: \'2023-12-23\'")
    @NotBlank
    private String deadline;

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

