package com.hillel.multi.presentation.controllers;

import com.hillel.api.StudentsManagerApi;
import com.hillel.model.StudentDTO;
import com.hillel.multi.service.StudentsManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class StudentsManagerController implements StudentsManagerApi {

    @Autowired
    private StudentsManagerService studentsManagerService;

    @Override
    public ResponseEntity<List<StudentDTO>> getStudents() {
        List<StudentDTO> studentDTOS = studentsManagerService.getStudents();
        return new ResponseEntity<>(studentDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StudentDTO> getStudentById(String id) {
        StudentDTO studentDTO = studentsManagerService.getStudentById(id);
        return ResponseEntity.ok(studentDTO);
    }

    @Override
    public ResponseEntity<StudentDTO> createStudent(StudentDTO studentDTO, URI xCallbackUrl) {
        StudentDTO body = studentsManagerService.addStudent(studentDTO);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<StudentDTO> updateStudent(String id, StudentDTO studentDTO) {
        StudentDTO body = studentsManagerService.updateStudent(id, studentDTO);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteStudent(String id) {
        studentsManagerService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
