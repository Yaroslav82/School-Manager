package com.hillel.multi.presentation.controllers;

import com.hillel.api.TimetableManagerApi;
import com.hillel.model.Lesson;
import com.hillel.multi.service.utils.ListToResponseConverter;
import com.hillel.multi.service.TimetableManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimetableManagerController implements TimetableManagerApi {

    @Autowired
    private TimetableManagerService timetableManagerService;

    @Override
    public ResponseEntity<List<Lesson>> getLessons(String group, String subject) {
        List<Lesson> lessons = timetableManagerService.getLessons(group, subject);
        return ListToResponseConverter.convert(lessons);
    }

    @Override
    public ResponseEntity<Lesson> addLesson(Lesson lesson) {
        return timetableManagerService.addLesson(lesson);
    }

    @Override
    public ResponseEntity<Lesson> updateLesson(Integer id, Lesson lesson) {
        return timetableManagerService.updateLesson(id, lesson);
    }
}
