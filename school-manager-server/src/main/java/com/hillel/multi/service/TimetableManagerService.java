package com.hillel.multi.service;

import com.hillel.model.LessonDTO;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TimetableManagerService {

    public List<LessonDTO> getLessons(String group, String subject) {
        return null;
    }

    public LessonDTO addLesson(LessonDTO lessonDTO) {
        // Example of using exception
        if (Objects.isNull(lessonDTO.getSubject())) {
            throw new MediaTypeException("Subject can not be null");
        }

        return lessonDTO;
    }

    public LessonDTO updateLesson(Integer id, LessonDTO lessonDTO) {
        // Example of using exception
        if (id < 0) {
            throw new NotFoundException("Lesson with id " + id + " is not found.");
        }

        return lessonDTO;
    }
}
