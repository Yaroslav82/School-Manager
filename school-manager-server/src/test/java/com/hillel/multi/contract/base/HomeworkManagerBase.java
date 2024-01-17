package com.hillel.multi.contract.base;

import com.hillel.model.HomeworkModel;
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
        List<HomeworkModel> homeworkModels = getListOfHomework();
        List<HomeworkModel> homeworksFiltered = getListOfHomework().stream()
                .filter(homeworkModel -> "GM-122".equals(homeworkModel.getGroup())).toList();
        HomeworkModel homeworkModelPositive = getPositiveHomework();
        HomeworkModel homeworkModelNegative = new HomeworkModel();

        Mockito.doReturn(homeworkModels).when(homeworkManagerService).getHomework(null, null);
        Mockito.doReturn(homeworksFiltered).when(homeworkManagerService).getHomework("GM-122", null);
        Mockito.doReturn(homeworkModelPositive).when(homeworkManagerService).addHomework(homeworkModelPositive);
        Mockito.doThrow(new MediaTypeException()).when(homeworkManagerService).addHomework(homeworkModelNegative);
        Mockito.doReturn(homeworkModelPositive).when(homeworkManagerService).updateHomework(1, homeworkModelPositive);
        Mockito.doThrow(new NotFoundException()).when(homeworkManagerService).updateHomework(-1, homeworkModelPositive);
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    private static HomeworkModel getPositiveHomework() {
        HomeworkModel homeworkModel = new HomeworkModel();
        homeworkModel.id(1);
        homeworkModel.name("Homework №1");
        homeworkModel.description("Read paragraph 48 and complete tasks 1, 2, 3");
        homeworkModel.group("GM-122");
        homeworkModel.subject("boolean_math");
        homeworkModel.deadline("2024-01-23");
        return homeworkModel;
    }

    private static List<HomeworkModel> getListOfHomework() {
        List<HomeworkModel> homeworkModels = new ArrayList<>();

        HomeworkModel homeworkModel1 = new HomeworkModel();
        homeworkModel1.id(1);
        homeworkModel1.name("Homework №1");
        homeworkModel1.description("Read paragraph 48 and complete tasks 1, 2, 3");
        homeworkModel1.group("GM-122");
        homeworkModel1.subject("boolean_math");
        homeworkModel1.deadline("2024-01-23");
        homeworkModels.add(homeworkModel1);

        HomeworkModel homeworkModel2 = new HomeworkModel();
        homeworkModel2.id(2);
        homeworkModel2.name("Homework №2");
        homeworkModel2.description("Read paragraph 12");
        homeworkModel2.group("GM-122");
        homeworkModel2.subject("boolean_math");
        homeworkModel2.deadline("2024-01-28");
        homeworkModels.add(homeworkModel2);

        HomeworkModel homeworkModel3 = new HomeworkModel();
        homeworkModel3.id(3);
        homeworkModel3.name("Homework №2");
        homeworkModel3.description("Read paragraph 1");
        homeworkModel3.group("EP-222");
        homeworkModel3.subject("ai_programming");
        homeworkModel3.deadline("2024-01-18");
        homeworkModels.add(homeworkModel3);

        return homeworkModels;
    }
}
