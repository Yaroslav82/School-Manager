package com.hillel.multi.contract.base;

import com.hillel.model.Homework;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.presentation.controllers.HomeworkManagerController;
import com.hillel.multi.service.HomeworkManagerService;
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

@WebMvcTest(controllers = HomeworkManagerController.class)
@AutoConfigureMockMvc
public class HomeworkManagerBase {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private HomeworkManagerController controller;

    @MockBean
    private HomeworkManagerService homeworkManagerService;

    @BeforeEach
    public void setUp() {
        List<Homework> homeworks = getListOfHomework();
        List<Homework> homeworksFiltered = getListOfHomework().stream()
                .filter(homework -> "GM-122".equals(homework.getGroup())).toList();
        Homework homeworkPositive = getPositiveHomework();
        Homework homeworkNegative = new Homework();

        Mockito.doReturn(homeworks).when(homeworkManagerService).getHomework(null, null);
        Mockito.doReturn(homeworksFiltered).when(homeworkManagerService).getHomework("GM-122", null);
        Mockito.doReturn(homeworkPositive).when(homeworkManagerService).addHomework(homeworkPositive);
        Mockito.doThrow(new MediaTypeException()).when(homeworkManagerService).addHomework(homeworkNegative);
        Mockito.doReturn(homeworkPositive).when(homeworkManagerService).updateHomework(1, homeworkPositive);
        Mockito.doThrow(new NotFoundException()).when(homeworkManagerService).updateHomework(-1, homeworkPositive);
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    private static Homework getPositiveHomework() {
        Homework homework = new Homework();
        homework.id(1);
        homework.name("Homework №1");
        homework.description("Read paragraph 48 and complete tasks 1, 2, 3");
        homework.group("GM-122");
        homework.subject("boolean_math");
        homework.deadline("2024-01-23");
        return homework;
    }

    private static List<Homework> getListOfHomework() {
        List<Homework> homeworks = new ArrayList<>();

        Homework homework1 = new Homework();
        homework1.id(1);
        homework1.name("Homework №1");
        homework1.description("Read paragraph 48 and complete tasks 1, 2, 3");
        homework1.group("GM-122");
        homework1.subject("boolean_math");
        homework1.deadline("2024-01-23");
        homeworks.add(homework1);

        Homework homework2 = new Homework();
        homework2.id(2);
        homework2.name("Homework №2");
        homework2.description("Read paragraph 12");
        homework2.group("GM-122");
        homework2.subject("boolean_math");
        homework2.deadline("2024-01-28");
        homeworks.add(homework2);

        Homework homework3 = new Homework();
        homework3.id(3);
        homework3.name("Homework №2");
        homework3.description("Read paragraph 1");
        homework3.group("EP-222");
        homework3.subject("ai_programming");
        homework3.deadline("2024-01-18");
        homeworks.add(homework3);

        return homeworks;
    }
}
