package com.hillel.multi.contract.base;

import com.hillel.model.HomeworkDTO;
import com.hillel.multi.configuration.TestSecurityConfig;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = HomeworkManagerController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestSecurityConfig.class)
public class HomeworkManagerBase {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private HomeworkManagerController controller;

    @MockBean
    private HomeworkManagerService homeworkManagerService;

    @BeforeEach
    public void setUp() {
        List<HomeworkDTO> homeworkDTOS = getListOfHomework();
        List<HomeworkDTO> homeworksFiltered = getListOfHomework().stream()
                .filter(homeworkModel -> "GM-122".equals(homeworkModel.getGroup())).toList();
        HomeworkDTO homeworkDTOPositive = getPositiveHomework();
        HomeworkDTO homeworkDTONegative = new HomeworkDTO();

        Mockito.doReturn(homeworkDTOS).when(homeworkManagerService).getHomework(null, null);
        Mockito.doReturn(homeworksFiltered).when(homeworkManagerService).getHomework("GM-122", null);
        Mockito.doReturn(homeworkDTOPositive).when(homeworkManagerService).addHomework(homeworkDTOPositive);
        Mockito.doThrow(new MediaTypeException()).when(homeworkManagerService).addHomework(homeworkDTONegative);
        Mockito.doReturn(homeworkDTOPositive).when(homeworkManagerService).updateHomework(1, homeworkDTOPositive);
        Mockito.doThrow(new NotFoundException()).when(homeworkManagerService).updateHomework(-1, homeworkDTOPositive);
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    private static HomeworkDTO getPositiveHomework() {
        HomeworkDTO homeworkDto = new HomeworkDTO();
        homeworkDto.id(1);
        homeworkDto.name("Homework №1");
        homeworkDto.description("Read paragraph 48 and complete tasks 1, 2, 3");
        homeworkDto.group("GM-122");
        homeworkDto.subject("boolean_math");
        homeworkDto.deadline("2024-01-23");
        return homeworkDto;
    }

    private static List<HomeworkDTO> getListOfHomework() {
        List<HomeworkDTO> homeworkDTOS = new ArrayList<>();

        HomeworkDTO homeworkDTO1 = new HomeworkDTO();
        homeworkDTO1.id(1);
        homeworkDTO1.name("Homework №1");
        homeworkDTO1.description("Read paragraph 48 and complete tasks 1, 2, 3");
        homeworkDTO1.group("GM-122");
        homeworkDTO1.subject("boolean_math");
        homeworkDTO1.deadline("2024-01-23");
        homeworkDTOS.add(homeworkDTO1);

        HomeworkDTO homeworkDTO2 = new HomeworkDTO();
        homeworkDTO2.id(2);
        homeworkDTO2.name("Homework №2");
        homeworkDTO2.description("Read paragraph 12");
        homeworkDTO2.group("GM-122");
        homeworkDTO2.subject("boolean_math");
        homeworkDTO2.deadline("2024-01-28");
        homeworkDTOS.add(homeworkDTO2);

        HomeworkDTO homeworkDTO3 = new HomeworkDTO();
        homeworkDTO3.id(3);
        homeworkDTO3.name("Homework №2");
        homeworkDTO3.description("Read paragraph 1");
        homeworkDTO3.group("EP-222");
        homeworkDTO3.subject("ai_programming");
        homeworkDTO3.deadline("2024-01-18");
        homeworkDTOS.add(homeworkDTO3);

        return homeworkDTOS;
    }
}
