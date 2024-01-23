package com.hillel.multi.service;

import com.hillel.model.LessonDTO;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.persistent.entities.Lesson;
import com.hillel.multi.persistent.repositories.TimetableManagerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@Validated
public class TimetableManagerService {

    @Autowired
    private TimetableManagerRepository timetableManagerRepository;

    public List<LessonDTO> getLessons(String group, String subject) {
        List<Lesson> lessons = timetableManagerRepository.getLessonsByGroupAndSubject(group, subject);
        return entityToDTO(lessons);
    }

    public LessonDTO addLesson(LessonDTO lessonDTO) {
        Lesson entity = timetableManagerRepository.save(dtoToEntity(lessonDTO));
        return entityToDTO(entity);
    }

    public LessonDTO updateLesson(Integer id, LessonDTO lessonDTO) {
        Lesson entity = getEntityById(id);

        return entityToDTO(timetableManagerRepository.save(entity));
    }

    public LessonDTO entityToDTO(@Valid Lesson lesson) {
        return null;
    }

    public List<LessonDTO> entityToDTO(@Valid List<Lesson> lessons) {
        return null;
    }

    public Lesson dtoToEntity(LessonDTO lessonDto) {
        return null;
    }

    private Lesson getEntityById(Integer id) {
        Lesson entity = timetableManagerRepository.getLessonById(id.longValue());
        if (Objects.nonNull(entity)) {
            return entity;
        } else {
            throw new NotFoundException("Lesson with id " + id + " is not found.");
        }
    }
}
