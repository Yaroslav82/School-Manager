package com.hillel.multi.presentation.controllers;

import com.hillel.api.StudentsManagerApi;
import com.hillel.model.MessageModel;
import com.hillel.model.StudentDTO;
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
    public ResponseEntity<List<StudentDTO>> getStudents() {
        List<StudentDTO> studentDTOS = studentsManagerService.getStudents();
        return new ResponseEntity<>(studentDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StudentDTO> getStudentById(Integer id) {
        StudentDTO studentDTO = studentsManagerService.getStudentById(id);
        return ResponseEntity.ok(studentDTO);
    }

    @Override
    public ResponseEntity<StudentDTO> createStudent(StudentDTO studentDTO, URI xCallbackUrl) {
        StudentDTO body = studentsManagerService.addStudent(studentDTO);
        if (Objects.nonNull(xCallbackUrl)) {
            MessageModel message = new MessageModel();
            message.setText(SUCCESS_MSG);
            callbackHandler.sendCallback(message, xCallbackUrl);
        }
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<StudentDTO> updateStudent(Integer id, StudentDTO studentDTO) {
        StudentDTO body = studentsManagerService.updateStudent(id, studentDTO);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
