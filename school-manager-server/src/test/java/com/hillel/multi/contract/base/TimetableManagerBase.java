package com.hillel.multi.contract.base;

import com.hillel.model.LessonDTO;
import com.hillel.multi.configuration.TestSecurityConfig;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.presentation.controllers.TimetableManagerController;
import com.hillel.multi.service.TimetableManagerService;
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

@WebMvcTest(controllers = TimetableManagerController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestSecurityConfig.class)
public class TimetableManagerBase {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TimetableManagerController controller;

    @MockBean
    private TimetableManagerService timetableManagerService;

    @BeforeEach
    public void setUp() {
        List<LessonDTO> timetable = getListOfAllLessons();
        List<LessonDTO> timetableFiltered = getListOfAllLessons().stream()
                .filter(lessonModel -> "EP-222".equals(lessonModel.getGroup())).toList();
        LessonDTO positiveLessonDTO = getPositiveLesson();
        LessonDTO negativeLessonDTO = new LessonDTO();

        Mockito.doReturn(timetable).when(timetableManagerService).getLessons(null, null);
        Mockito.doReturn(timetableFiltered).when(timetableManagerService).getLessons("EP-222", null);
        Mockito.doReturn(positiveLessonDTO).when(timetableManagerService).addLesson(positiveLessonDTO);
        Mockito.doThrow(new MediaTypeException()).when(timetableManagerService).addLesson(negativeLessonDTO);
        Mockito.doReturn(positiveLessonDTO).when(timetableManagerService).updateLesson(3, positiveLessonDTO);
        Mockito.doThrow(new NotFoundException()).when(timetableManagerService).updateLesson(-1, positiveLessonDTO);

        RestAssuredMockMvc.webAppContextSetup(context);
    }

    private static LessonDTO getPositiveLesson() {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.id(3);
        lessonDTO.timestamp("2024-01-10 11:20:00");
        lessonDTO.group("RPA-123");
        lessonDTO.subject("boolean_math");
        return lessonDTO;
    }

    private static List<LessonDTO> getListOfAllLessons() {
        List<LessonDTO> lessonDTOS = new ArrayList<>();

        LessonDTO lessonDTO1 = new LessonDTO();
        lessonDTO1.id(1);
        lessonDTO1.timestamp("2024-01-10 08:00:00");
        lessonDTO1.group("EP-222");
        lessonDTO1.subject("ai_programing");
        lessonDTOS.add(lessonDTO1);

        LessonDTO lessonDTO2 = new LessonDTO();
        lessonDTO2.id(2);
        lessonDTO2.timestamp("2024-01-10 09:45:00");
        lessonDTO2.group("EP-222");
        lessonDTO2.subject("database_design");
        lessonDTOS.add(lessonDTO2);

        LessonDTO lessonDTO3 = new LessonDTO();
        lessonDTO3.id(3);
        lessonDTO3.timestamp("2024-01-10 11:20:00");
        lessonDTO3.group("RPA-123");
        lessonDTO3.subject("boolean_math");
        lessonDTOS.add(lessonDTO3);

        return lessonDTOS;
    }
}
