package com.hillel.multi.service;

import com.hillel.model.LessonDTO;
import com.hillel.multi.persistent.entities.Lesson;
import com.hillel.multi.persistent.repositories.TimetableManagerRepository;
import com.hillel.multi.service.mappers.LessonMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Validated
@Slf4j
public class TimetableManagerService {

    @Autowired
    private TimetableManagerRepository timetableManagerRepository;

    public List<LessonDTO> getLessons(String group, String subject) {
        log.info("Getting lessons for group '{}' and subject '{}'", group, subject);
        List<Lesson> lessons = timetableManagerRepository.getLessonsByGroupAndSubject(group, subject);
        return entityToDTO(lessons);
    }

    public LessonDTO addLesson(LessonDTO lessonDTO) {
        Lesson entity = timetableManagerRepository.save(dtoToEntity(lessonDTO));
        log.info("Lesson with id '{}' added", entity.getId());
        return entityToDTO(entity);
    }

    public LessonDTO updateLesson(Integer id, LessonDTO lessonDTO) {
        Lesson entity = getEntityById(id);
        LessonMapper.INSTANCE.updateIntoLesson(entity, lessonDTO);
        entity = timetableManagerRepository.save(entity);
        log.info("Lesson with id '{}' updated", id);
        return entityToDTO(entity);
    }

    public void deleteLesson(Integer id) {
        timetableManagerRepository.deleteById(Long.valueOf(id));
        log.info("Lesson with id '{}' was deleted", id);
    }

    public LessonDTO entityToDTO(@Valid Lesson lesson) {
        return LessonMapper.INSTANCE.lessonToDto(lesson);
    }

    public List<LessonDTO> entityToDTO(@Valid List<Lesson> lessons) {
        return LessonMapper.INSTANCE.lessonsToDto(lessons);
    }

    public Lesson dtoToEntity(LessonDTO lessonDto) {
        return LessonMapper.INSTANCE.dtoToLesson(lessonDto);
    }

    private Lesson getEntityById(Integer id) {
        return timetableManagerRepository.getLessonById(id.longValue()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson with id " + id + " is not found.")
        );
    }
}
