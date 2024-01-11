package com.hillel.multi.service;

import com.hillel.model.Student;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentsManagerService {

    public List<Student> getStudents() {
        return null;
    }

    public Student getStudentById(Integer id) {
        // Example of using exception
        if (id < 0) {
            throw new NotFoundException("Student with id " + id + " is not found.");
        }

        return null;
    }

    public Student addStudent(Student student) {
        // Example of using exception
        if (Objects.isNull(student.getFirstName())) {
            throw new MediaTypeException("First name can not be null");
        }

        return student;
    }

    public Student updateStudent(Integer id, Student student) {
        // Example of using exception
        if (id < 0) {
            throw new NotFoundException("Student with id " + id + " is not found.");
        }

        return student;
    }
}
