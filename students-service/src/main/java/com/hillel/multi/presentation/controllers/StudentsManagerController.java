package com.hillel.multi.presentation.controllers;

import com.hillel.api.StudentsManagerApi;
import com.hillel.model.StudentDTO;
import com.hillel.multi.service.StudentsManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class StudentsManagerController implements StudentsManagerApi {

    @Autowired
    private StudentsManagerService studentsManagerService;

    @Override
    public ResponseEntity<List<StudentDTO>> getStudents() {
        log.info("Received request to get all students");
        List<StudentDTO> studentDTOS = studentsManagerService.getStudents();
        return new ResponseEntity<>(studentDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StudentDTO> getStudentById(String id) {
        log.info("Received request to get student with id '{}'", id);
        StudentDTO studentDTO = studentsManagerService.getStudentById(id);
        return ResponseEntity.ok(studentDTO);
    }

    @Override
    public ResponseEntity<StudentDTO> createStudent(StudentDTO studentDTO, URI xCallbackUrl) {
        log.info("Received request to add student");
        StudentDTO body = studentsManagerService.addStudent(studentDTO);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<StudentDTO> updateStudent(String id, StudentDTO studentDTO) {
        log.info("Received request to update student with id '{}'", id);
        StudentDTO body = studentsManagerService.updateStudent(id, studentDTO);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteStudent(String id) {
        log.info("Received request to delete student with id '{}'", id);
        studentsManagerService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
