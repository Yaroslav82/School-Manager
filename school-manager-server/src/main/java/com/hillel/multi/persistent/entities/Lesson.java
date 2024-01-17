package com.hillel.multi.persistent.entities;

import com.hillel.multi.persistent.annotations.ValidGroupName;
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
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "timestamp")
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])) (2[0-3]|[0-1]?[\\d]):[0-5][\\d]:[0-5][\\d]$")
    private String timestamp;

    @Column(name = "group_name")
    @ValidGroupName
    private String groupName;

    @Column(name = "subject")
    @NotBlank
    private String subject;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(timestamp, lesson.timestamp) && Objects.equals(groupName, lesson.groupName) && Objects.equals(subject, lesson.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, groupName, subject);
    }
}
