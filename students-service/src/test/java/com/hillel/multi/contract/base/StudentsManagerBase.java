package com.hillel.multi.contract.base;

import com.hillel.model.StudentDTO;
import com.hillel.multi.presentation.controllers.StudentsManagerController;
import com.hillel.multi.service.StudentsManagerService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@WebMvcTest(controllers = StudentsManagerController.class)
@AutoConfigureMockMvc
public class StudentsManagerBase {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private StudentsManagerController controller;

    @MockBean
    private StudentsManagerService studentsManagerService;

    @BeforeEach
    public void setUp() {
        StudentDTO studentDTONegative = new StudentDTO();
        StudentDTO studentDTOPositive = getPositiveStudent();
        List<StudentDTO> studentsList = getListOfStudents();

        // get endpoints
        Mockito.doReturn(studentsList).when(studentsManagerService).getStudents();
        Mockito.doReturn(studentDTOPositive).when(studentsManagerService).getStudentById("1");
        Mockito.doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(studentsManagerService).getStudentById("-1");

        // add endpoint
        Mockito.doReturn(studentDTOPositive).when(studentsManagerService).addStudent(studentDTOPositive);
        Mockito.doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST)).when(studentsManagerService).addStudent(studentDTONegative);

        // update endpoint
        Mockito.doReturn(studentDTOPositive).when(studentsManagerService).updateStudent("1", studentDTOPositive);
        Mockito.doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(studentsManagerService).updateStudent("-1", studentDTOPositive);

        // delete endpoint
        Mockito.doNothing().when(studentsManagerService).deleteStudent("1");
        Mockito.doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(studentsManagerService).deleteStudent("-1");
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    private static StudentDTO getPositiveStudent() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.id("1");
        studentDTO.firstName("Luke");
        studentDTO.lastName("Skywalker");
        studentDTO.group("GM-122");
        return studentDTO;
    }

    private static List<StudentDTO> getListOfStudents() {
        List<StudentDTO> studentDTOS = new ArrayList<>();

        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.id("1");
        studentDTO1.firstName("Luke");
        studentDTO1.lastName("Skywalker");
        studentDTO1.group("GM-122");
        studentDTOS.add(studentDTO1);

        StudentDTO studentDTO2 = new StudentDTO();
        studentDTO2.id("2");
        studentDTO2.firstName("Han");
        studentDTO2.lastName("Solo");
        studentDTO2.group("GM-122");
        studentDTOS.add(studentDTO2);

        StudentDTO studentDTO3 = new StudentDTO();
        studentDTO3.id("3");
        studentDTO3.firstName("Dart");
        studentDTO3.lastName("Vader");
        studentDTO3.group("EP-222");
        studentDTOS.add(studentDTO3);

        return studentDTOS;
    }
}