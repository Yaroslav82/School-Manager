package com.hillel.multi.presentation.controllers;

import com.hillel.api.StudentsManagerApi;
import com.hillel.model.Message;
import com.hillel.model.Student;
import com.hillel.multi.service.utils.ListToResponseConverter;
import com.hillel.multi.service.utils.CallbackHandler;
import com.hillel.multi.service.StudentsManagerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final static String ERROR_MSG = "Something went wrong :(";

    @Override
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentsManagerService.getStudents();
        return ListToResponseConverter.convert(students);
    }

    @Override
    public ResponseEntity<Student> getStudentById(Integer id) {
        Student student = studentsManagerService.getStudentById(id);
        if (Objects.isNull(student)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @Override
    public ResponseEntity<Student> createStudent(Student student, URI xCallbackUrl) {
        ResponseEntity<Student> response = studentsManagerService.addStudent(student);
        if (Objects.nonNull(xCallbackUrl)) {
            Message message = new Message();
            if (response.getStatusCode().is2xxSuccessful()) {
                message.setText(SUCCESS_MSG);
            } else {
                message.setText(ERROR_MSG);
            }
            callbackHandler.sendCallback(message, xCallbackUrl);
        }
        return response;
    }

    @Override
    public ResponseEntity<Student> updateStudent(Integer id, Student student) {
        return studentsManagerService.updateStudent(id, student);
    }
}
