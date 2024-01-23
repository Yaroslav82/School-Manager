package com.hillel.multi.service;

import com.hillel.model.StudentDTO;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.persistent.entities.Student;
import com.hillel.multi.persistent.repositories.StudentManagerRepository;
import com.hillel.multi.service.mappers.StudentMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@Validated
public class StudentsManagerService {

    @Autowired
    private StudentManagerRepository studentManagerRepository;

    public List<StudentDTO> getStudents() {
        List<Student> students = studentManagerRepository.getStudents();
        return entityToDTO(students);
    }

    public StudentDTO getStudentById(Integer id) {
        return entityToDTO(getEntityById(id));
    }

    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student entity = studentManagerRepository.save(dtoToEntity(studentDTO));
        return entityToDTO(entity);
    }

    public StudentDTO updateStudent(Integer id, StudentDTO studentDTO) {
        Student entity = getEntityById(id);
        StudentMapper.INSTANCE.updateIntoStudent(entity, studentDTO);
        return entityToDTO(studentManagerRepository.save(entity));
    }

    public StudentDTO entityToDTO(@Valid Student student) {
        return StudentMapper.INSTANCE.studentToDto(student);
    }

    public List<StudentDTO> entityToDTO(List<@Valid Student> students) {
        return StudentMapper.INSTANCE.studentsToDto(students);
    }

    @Valid
    public Student dtoToEntity(StudentDTO studentDto) {
        return StudentMapper.INSTANCE.dtoToStudent(studentDto);
    }

    private Student getEntityById(Integer id) {
        Student entity = studentManagerRepository.getStudentById(id.longValue());
        if (Objects.nonNull(entity)) {
            return entity;
        } else {
            throw new NotFoundException("Student with id " + id + " is not found.");
        }
    }
}
