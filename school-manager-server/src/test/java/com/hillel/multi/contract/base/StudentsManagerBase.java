package com.hillel.multi.contract.base;

import com.hillel.model.StudentModel;
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
        StudentModel studentModelNegative = new StudentModel();
        StudentModel studentModelPositive = getPositiveStudent();
        List<StudentModel> studentsList = getListOfStudents();

        Mockito.doReturn(studentsList).when(studentsManagerService).getStudents();
        Mockito.doReturn(studentModelPositive).when(studentsManagerService).getStudentById(1);
        Mockito.doThrow(new NotFoundException()).when(studentsManagerService).getStudentById(-1);
        Mockito.doReturn(studentModelPositive).when(studentsManagerService).addStudent(studentModelPositive);
        Mockito.doThrow(new MediaTypeException()).when(studentsManagerService).addStudent(studentModelNegative);
        Mockito.doReturn(studentModelPositive).when(studentsManagerService).updateStudent(1, studentModelPositive);
        Mockito.doThrow(new NotFoundException()).when(studentsManagerService).updateStudent(-1, studentModelPositive);

        RestAssuredMockMvc.webAppContextSetup(context);
    }

    private static StudentModel getPositiveStudent() {
        StudentModel studentModel = new StudentModel();
        studentModel.id(1);
        studentModel.firstName("Luke");
        studentModel.lastName("Skywalker");
        studentModel.group("GM-122");
        return studentModel;
    }

    private static List<StudentModel> getListOfStudents() {
        List<StudentModel> studentModels = new ArrayList<>();

        StudentModel studentModel1 = new StudentModel();
        studentModel1.id(1);
        studentModel1.firstName("Luke");
        studentModel1.lastName("Skywalker");
        studentModel1.group("GM-122");
        studentModels.add(studentModel1);

        StudentModel studentModel2 = new StudentModel();
        studentModel2.id(2);
        studentModel2.firstName("Han");
        studentModel2.lastName("Solo");
        studentModel2.group("GM-122");
        studentModels.add(studentModel2);

        StudentModel studentModel3 = new StudentModel();
        studentModel3.id(3);
        studentModel3.firstName("Dart");
        studentModel3.lastName("Vader");
        studentModel3.group("EP-222");
        studentModels.add(studentModel3);

        return studentModels;
    }
}
