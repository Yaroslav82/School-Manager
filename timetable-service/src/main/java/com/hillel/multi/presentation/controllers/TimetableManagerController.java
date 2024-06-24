package com.hillel.multi.presentation.controllers;

import com.hillel.api.TimetableManagerApi;
import com.hillel.model.LessonDTO;
import com.hillel.multi.service.TimetableManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class TimetableManagerController implements TimetableManagerApi {

    @Autowired
    private TimetableManagerService timetableManagerService;

    @Override
    public ResponseEntity<List<LessonDTO>> getLessons(String group, String subject) {
        log.info("Received request to get lessons for group '{}' and subject '{}'", group, subject);
        List<LessonDTO> lessonDTOS = timetableManagerService.getLessons(group, subject);
        return new ResponseEntity<>(lessonDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LessonDTO> addLesson(LessonDTO lessonDTO) {
        log.info("Received request to add lesson");
        LessonDTO body = timetableManagerService.addLesson(lessonDTO);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LessonDTO> updateLesson(Integer id, LessonDTO lessonDTO) {
        log.info("Received request to update lesson with id '{}'", id);
        LessonDTO body = timetableManagerService.updateLesson(id, lessonDTO);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteLesson(Integer id) {
        log.info("Received request to delete lesson with id '{}'", id);
        timetableManagerService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }
}
