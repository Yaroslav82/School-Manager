package com.hillel.multi.service;

import com.hillel.model.Lesson;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TimetableManagerService {

    public List<Lesson> getLessons(String group, String subject) {
        return null;
    }

    public Lesson addLesson(Lesson lesson) {
        // Example of using exception
        if (Objects.isNull(lesson.getSubject())) {
            throw new MediaTypeException("Subject can not be null");
        }

        return lesson;
    }

    public Lesson updateLesson(Integer id, Lesson lesson) {
        // Example of using exception
        if (id < 0) {
            throw new NotFoundException("Lesson with id " + id + " is not found.");
        }

        return lesson;
    }
}
