package com.hillel.multi.contract.base;

import com.hillel.model.Lesson;
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
        List<Lesson> timetable = getListOfAllLessons();
        List<Lesson> timetableFiltered = getListOfAllLessons().stream()
                .filter(lesson -> "EP-222".equals(lesson.getGroup())).toList();
        Lesson positiveLesson = getPositiveLesson();
        Lesson negativeLesson = new Lesson();

        Mockito.doReturn(timetable).when(timetableManagerService).getLessons(null, null);
        Mockito.doReturn(timetableFiltered).when(timetableManagerService).getLessons("EP-222", null);
        Mockito.doReturn(positiveLesson).when(timetableManagerService).addLesson(positiveLesson);
        Mockito.doThrow(new MediaTypeException()).when(timetableManagerService).addLesson(negativeLesson);
        Mockito.doReturn(positiveLesson).when(timetableManagerService).updateLesson(3, positiveLesson);
        Mockito.doThrow(new NotFoundException()).when(timetableManagerService).updateLesson(-1, positiveLesson);

        RestAssuredMockMvc.webAppContextSetup(context);
    }

    private static Lesson getPositiveLesson() {
        Lesson lesson = new Lesson();
        lesson.id(3);
        lesson.timestamp("2024-01-10 11:20:00");
        lesson.group("RPA-123");
        lesson.subject("boolean_math");
        return lesson;
    }

    private static List<Lesson> getListOfAllLessons() {
        List<Lesson> lessons = new ArrayList<>();

        Lesson lesson1 = new Lesson();
        lesson1.id(1);
        lesson1.timestamp("2024-01-10 08:00:00");
        lesson1.group("EP-222");
        lesson1.subject("ai_programing");
        lessons.add(lesson1);

        Lesson lesson2 = new Lesson();
        lesson2.id(2);
        lesson2.timestamp("2024-01-10 09:45:00");
        lesson2.group("EP-222");
        lesson2.subject("database_design");
        lessons.add(lesson2);

        Lesson lesson3 = new Lesson();
        lesson3.id(3);
        lesson3.timestamp("2024-01-10 11:20:00");
        lesson3.group("RPA-123");
        lesson3.subject("boolean_math");
        lessons.add(lesson3);

        return lessons;
    }
}
