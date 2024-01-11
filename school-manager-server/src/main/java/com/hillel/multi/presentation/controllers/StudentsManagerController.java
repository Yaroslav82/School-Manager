package com.hillel.multi.presentation.controllers;

import com.hillel.api.StudentsManagerApi;
import com.hillel.model.Message;
import com.hillel.model.Student;
import com.hillel.multi.service.StudentsManagerService;
import com.hillel.multi.service.utils.CallbackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
public class StudentsManagerController implements StudentsManagerApi {

    @Autowired
    private StudentsManagerService studentsManagerService;

    @Autowired
    private CallbackHandler callbackHandler;

    private final static String SUCCESS_MSG = "Student was successfully created";

    @Override
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentsManagerService.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Student> getStudentById(Integer id) {
        Student student = studentsManagerService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @Override
    public ResponseEntity<Student> createStudent(Student student, URI xCallbackUrl) {
        Student body = studentsManagerService.addStudent(student);
        if (Objects.nonNull(xCallbackUrl)) {
            Message message = new Message();
            message.setText(SUCCESS_MSG);
            callbackHandler.sendCallback(message, xCallbackUrl);
        }
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Student> updateStudent(Integer id, Student student) {
        Student body = studentsManagerService.updateStudent(id, student);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
