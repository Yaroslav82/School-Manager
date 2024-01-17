package com.hillel.multi.presentation.controllers;

import com.hillel.api.StudentsManagerApi;
import com.hillel.model.MessageModel;
import com.hillel.model.StudentModel;
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
    public ResponseEntity<List<StudentModel>> getStudents() {
        List<StudentModel> studentModels = studentsManagerService.getStudents();
        return new ResponseEntity<>(studentModels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StudentModel> getStudentById(Integer id) {
        StudentModel studentModel = studentsManagerService.getStudentById(id);
        return ResponseEntity.ok(studentModel);
    }

    @Override
    public ResponseEntity<StudentModel> createStudent(StudentModel studentModel, URI xCallbackUrl) {
        StudentModel body = studentsManagerService.addStudent(studentModel);
        if (Objects.nonNull(xCallbackUrl)) {
            MessageModel message = new MessageModel();
            message.setText(SUCCESS_MSG);
            callbackHandler.sendCallback(message, xCallbackUrl);
        }
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<StudentModel> updateStudent(Integer id, StudentModel studentModel) {
        StudentModel body = studentsManagerService.updateStudent(id, studentModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
