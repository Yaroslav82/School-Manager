package com.hillel.multi.persistent.repositories;

import com.hillel.multi.persistent.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentManagerRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.id = :id")
    Student getStudentById(@Param("id") Long id);

    @Query("SELECT s FROM Student s")
    List<Student> getStudents();
}
