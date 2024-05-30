package com.hillel.multi.service;

import com.hillel.model.StudentDTO;
import com.hillel.multi.persistent.entities.Student;
import com.hillel.multi.persistent.repositories.StudentManagerRepository;
import com.hillel.multi.service.mappers.StudentMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Validated
@AllArgsConstructor
public class StudentsManagerService {

    private StudentManagerRepository studentManagerRepository;

    public List<StudentDTO> getStudents() {
        List<Student> students = studentManagerRepository.findAll();
        return entityToDTO(students);
    }

    public StudentDTO getStudentById(String id) {
        return entityToDTO(getEntityById(id));
    }

    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student entity = studentManagerRepository.save(dtoToEntity(studentDTO));
        return entityToDTO(entity);
    }

    public StudentDTO updateStudent(String id, StudentDTO studentDTO) {
        Student entity = getEntityById(id);
        StudentMapper.INSTANCE.updateIntoStudent(entity, studentDTO);
        return entityToDTO(studentManagerRepository.save(entity));
    }

    public void deleteStudent(String id) {
        Student entity = getEntityById(id);
        studentManagerRepository.delete(entity);
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

    private Student getEntityById(String id) {
        return studentManagerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with id " + id + " not found")
        );
    }
}
