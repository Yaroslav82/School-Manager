package com.hillel.multi.service;

import com.hillel.model.HomeworkDTO;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.persistent.entities.Homework;
import com.hillel.multi.persistent.repositories.HomeworkManagerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@Validated
public class HomeworkManagerService {

    @Autowired
    private HomeworkManagerRepository homeworkManagerRepository;

    public List<HomeworkDTO> getHomework(String group, String subject) {
        List<Homework> homeworkList = homeworkManagerRepository.getHomeworkByGroupAndSubject(group, subject);
        return entityToDTO(homeworkList);
    }

    public HomeworkDTO addHomework(HomeworkDTO homeworkDto) {
        Homework entity = homeworkManagerRepository.save(dtoToEntity(homeworkDto));
        return entityToDTO(entity);
    }

    public HomeworkDTO updateHomework(Integer id, HomeworkDTO homeworkDto) {
        Homework entity = getEntityById(id);

        return entityToDTO(homeworkManagerRepository.save(entity));
    }

    public HomeworkDTO entityToDTO(@Valid Homework homework) {
        return null;
    }

    public List<HomeworkDTO> entityToDTO(List<@Valid Homework> homework) {
        return null;
    }

    @Valid
    public Homework dtoToEntity(HomeworkDTO homeworkDto) {
        return null;
    }

    private Homework getEntityById(Integer id) {
        Homework entity = homeworkManagerRepository.getHomeworkById(id.longValue());
        if (Objects.nonNull(entity)) {
            return entity;
        } else {
            throw new NotFoundException("Homework with id " + id + " is not found.");
        }
    }
}
