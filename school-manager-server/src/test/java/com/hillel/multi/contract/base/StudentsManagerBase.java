package com.hillel.multi.contract.base;

import com.hillel.model.Student;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.presentation.controllers.StudentsManagerController;
import com.hillel.multi.service.StudentsManagerService;
import com.hillel.multi.service.utils.CallbackHandler;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.context.WebApplicationContext;

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

    @MockBean
    private CallbackHandler callbackHandler;

    @BeforeEach
    public void setUp() {
        Student studentNegative = new Student();
        Student studentPositive = getPositiveStudent();
        List<Student> studentsList = getListOfStudents();

        Mockito.doReturn(studentsList).when(studentsManagerService).getStudents();
        Mockito.doReturn(studentPositive).when(studentsManagerService).getStudentById(1);
        Mockito.doThrow(new NotFoundException()).when(studentsManagerService).getStudentById(-1);
        Mockito.doReturn(studentPositive).when(studentsManagerService).addStudent(studentPositive);
        Mockito.doThrow(new MediaTypeException()).when(studentsManagerService).addStudent(studentNegative);
        Mockito.doReturn(studentPositive).when(studentsManagerService).updateStudent(1, studentPositive);
        Mockito.doThrow(new NotFoundException()).when(studentsManagerService).updateStudent(-1, studentPositive);

        RestAssuredMockMvc.webAppContextSetup(context);
    }

    private static Student getPositiveStudent() {
        Student student = new Student();
        student.id(1);
        student.firstName("Luke");
        student.lastName("Skywalker");
        student.group("GM-122");
        return student;
    }

    private static List<Student> getListOfStudents() {
        List<Student> students = new ArrayList<>();

        Student student1 = new Student();
        student1.id(1);
        student1.firstName("Luke");
        student1.lastName("Skywalker");
        student1.group("GM-122");
        students.add(student1);

        Student student2 = new Student();
        student2.id(2);
        student2.firstName("Han");
        student2.lastName("Solo");
        student2.group("GM-122");
        students.add(student2);

        Student student3 = new Student();
        student3.id(3);
        student3.firstName("Dart");
        student3.lastName("Vader");
        student3.group("EP-222");
        students.add(student3);

        return students;
    }
}
