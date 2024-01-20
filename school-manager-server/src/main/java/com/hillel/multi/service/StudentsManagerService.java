package com.hillel.multi.service;

import com.hillel.model.StudentDTO;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.persistent.entities.Student;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@Validated
public class StudentsManagerService {

    public List<StudentDTO> getStudents() {
        return null;
    }

    public StudentDTO getStudentById(Integer id) {
        // Example of using exception
        if (id < 0) {
            throw new NotFoundException("Student with id " + id + " is not found.");
        }

        return null;
    }

    public StudentDTO addStudent(StudentDTO studentDTO) {
        // Example of using exception
        if (Objects.isNull(studentDTO.getFirstName())) {
            throw new MediaTypeException("First name can not be null");
        }

        return studentDTO;
    }

    public StudentDTO updateStudent(Integer id, StudentDTO studentDTO) {
        // Example of using exception
        if (id < 0) {
            throw new NotFoundException("Student with id " + id + " is not found.");
        }

        return studentDTO;
    }

    public StudentDTO entityToDTO(@Valid Student student) {
        return null;
    }

    public Student dtoToEntity(StudentDTO studentDto) {
        return null;
    }
}
