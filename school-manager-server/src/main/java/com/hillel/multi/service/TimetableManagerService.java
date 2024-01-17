package com.hillel.multi.service;

import com.hillel.model.LessonModel;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TimetableManagerService {

    public List<LessonModel> getLessons(String group, String subject) {
        return null;
    }

    public LessonModel addLesson(LessonModel lessonModel) {
        // Example of using exception
        if (Objects.isNull(lessonModel.getSubject())) {
            throw new MediaTypeException("Subject can not be null");
        }

        return lessonModel;
    }

    public LessonModel updateLesson(Integer id, LessonModel lessonModel) {
        // Example of using exception
        if (id < 0) {
            throw new NotFoundException("Lesson with id " + id + " is not found.");
        }

        return lessonModel;
    }
}
