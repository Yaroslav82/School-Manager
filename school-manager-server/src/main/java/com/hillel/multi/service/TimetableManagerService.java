package com.hillel.multi.service;

import com.hillel.model.Lesson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableManagerService {

    public List<Lesson> getLessons(String group, String subject) {
        return null;
    }

    public ResponseEntity<Lesson> addLesson(Lesson lesson) {
        return new ResponseEntity<>(lesson, HttpStatus.CREATED);
    }

    public ResponseEntity<Lesson> updateLesson(Integer id, Lesson lesson) {
        return new ResponseEntity<>(lesson, HttpStatus.OK);
    }
}
