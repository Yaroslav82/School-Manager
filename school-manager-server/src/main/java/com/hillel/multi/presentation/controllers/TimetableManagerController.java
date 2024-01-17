package com.hillel.multi.presentation.controllers;

import com.hillel.api.TimetableManagerApi;
import com.hillel.model.LessonModel;
import com.hillel.multi.service.TimetableManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimetableManagerController implements TimetableManagerApi {

    @Autowired
    private TimetableManagerService timetableManagerService;

    @Override
    public ResponseEntity<List<LessonModel>> getLessons(String group, String subject) {
        List<LessonModel> lessonModels = timetableManagerService.getLessons(group, subject);
        return new ResponseEntity<>(lessonModels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LessonModel> addLesson(LessonModel lessonModel) {
        LessonModel body = timetableManagerService.addLesson(lessonModel);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LessonModel> updateLesson(Integer id, LessonModel lessonModel) {
        LessonModel body = timetableManagerService.updateLesson(id, lessonModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
