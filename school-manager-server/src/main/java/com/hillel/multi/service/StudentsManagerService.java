package com.hillel.multi.service;

import com.hillel.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsManagerService {

    public List<Student> getStudents() {
        return null;
    }

    public Student getStudentById(Integer id) {
        return null;
    }

    public ResponseEntity<Student> addStudent(Student student) {
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    public ResponseEntity<Student> updateStudent(Integer id, Student student) {
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
