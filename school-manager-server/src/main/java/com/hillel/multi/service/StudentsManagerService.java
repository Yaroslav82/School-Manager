package com.hillel.multi.service;

import com.hillel.model.StudentModel;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentsManagerService {

    public List<StudentModel> getStudents() {
        return null;
    }

    public StudentModel getStudentById(Integer id) {
        // Example of using exception
        if (id < 0) {
            throw new NotFoundException("Student with id " + id + " is not found.");
        }

        return null;
    }

    public StudentModel addStudent(StudentModel studentModel) {
        // Example of using exception
        if (Objects.isNull(studentModel.getFirstName())) {
            throw new MediaTypeException("First name can not be null");
        }

        return studentModel;
    }

    public StudentModel updateStudent(Integer id, StudentModel studentModel) {
        // Example of using exception
        if (id < 0) {
            throw new NotFoundException("Student with id " + id + " is not found.");
        }

        return studentModel;
    }
}
