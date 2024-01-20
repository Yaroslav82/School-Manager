package com.hillel.multi.service;

import com.hillel.model.LessonDTO;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.persistent.entities.Lesson;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@Validated
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

    public LessonDTO entityToDTO(@Valid Lesson lesson) {
        return null;
    }

    public Lesson dtoToEntity(LessonDTO lessonDto) {
        return null;
    }
}
