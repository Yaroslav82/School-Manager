package com.hillel.multi.contract.base;

import com.hillel.model.LessonModel;
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
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = TimetableManagerController.class)
@AutoConfigureMockMvc
public class TimetableManagerBase {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TimetableManagerController controller;

    @MockBean
    private TimetableManagerService timetableManagerService;

    @BeforeEach
    public void setUp() {
        List<LessonModel> timetable = getListOfAllLessons();
        List<LessonModel> timetableFiltered = getListOfAllLessons().stream()
                .filter(lessonModel -> "EP-222".equals(lessonModel.getGroup())).toList();
        LessonModel positiveLessonModel = getPositiveLesson();
        LessonModel negativeLessonModel = new LessonModel();

        Mockito.doReturn(timetable).when(timetableManagerService).getLessons(null, null);
        Mockito.doReturn(timetableFiltered).when(timetableManagerService).getLessons("EP-222", null);
        Mockito.doReturn(positiveLessonModel).when(timetableManagerService).addLesson(positiveLessonModel);
        Mockito.doThrow(new MediaTypeException()).when(timetableManagerService).addLesson(negativeLessonModel);
        Mockito.doReturn(positiveLessonModel).when(timetableManagerService).updateLesson(3, positiveLessonModel);
        Mockito.doThrow(new NotFoundException()).when(timetableManagerService).updateLesson(-1, positiveLessonModel);

        RestAssuredMockMvc.webAppContextSetup(context);
    }

    private static LessonModel getPositiveLesson() {
        LessonModel lessonModel = new LessonModel();
        lessonModel.id(3);
        lessonModel.timestamp("2024-01-10 11:20:00");
        lessonModel.group("RPA-123");
        lessonModel.subject("boolean_math");
        return lessonModel;
    }

    private static List<LessonModel> getListOfAllLessons() {
        List<LessonModel> lessonModels = new ArrayList<>();

        LessonModel lessonModel1 = new LessonModel();
        lessonModel1.id(1);
        lessonModel1.timestamp("2024-01-10 08:00:00");
        lessonModel1.group("EP-222");
        lessonModel1.subject("ai_programing");
        lessonModels.add(lessonModel1);

        LessonModel lessonModel2 = new LessonModel();
        lessonModel2.id(2);
        lessonModel2.timestamp("2024-01-10 09:45:00");
        lessonModel2.group("EP-222");
        lessonModel2.subject("database_design");
        lessonModels.add(lessonModel2);

        LessonModel lessonModel3 = new LessonModel();
        lessonModel3.id(3);
        lessonModel3.timestamp("2024-01-10 11:20:00");
        lessonModel3.group("RPA-123");
        lessonModel3.subject("boolean_math");
        lessonModels.add(lessonModel3);

        return lessonModels;
    }
}
