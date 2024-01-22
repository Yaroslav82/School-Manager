package com.hillel.multi.service;

import com.hillel.model.HomeworkDTO;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.persistent.entities.Homework;
import com.hillel.multi.persistent.repositories.HomeworkManagerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Validated
public class HomeworkManagerService {

    @Autowired
    private HomeworkManagerRepository homeworkManagerRepository;

    public HomeworkDTO getHomeworkById(Integer id) {
        Homework entity = homeworkManagerRepository.getHomeworkById(id.longValue());
        if (Objects.nonNull(entity)) {
            return entityToDTO(entity);
        } else {
            throw new NotFoundException("Homework with id " + id + " is not found.");
        }
    }

    public List<HomeworkDTO> getHomework(String group, String subject) {
        List<Homework> homeworkList = homeworkManagerRepository.getHomeworkByGroupAndSubject(group, subject);
        return entityToDTO(homeworkList);
    }

    public HomeworkDTO addHomework(HomeworkDTO homeworkDto) {
        Homework entity = homeworkManagerRepository.save(dtoToEntity(homeworkDto));
        return entityToDTO(entity);
    }

    public HomeworkDTO updateHomework(Integer id, HomeworkDTO homeworkDto) {
        HomeworkDTO existingHomework = getHomeworkById(id);

        homeworkDto.setId(id);
        if (homeworkDto.getName() == null) homeworkDto.setName(existingHomework.getName());
        if (homeworkDto.getDescription() == null) homeworkDto.setDescription(existingHomework.getDescription());
        if (homeworkDto.getSubject() == null) homeworkDto.setSubject(existingHomework.getSubject());
        if (homeworkDto.getGroup() == null) homeworkDto.setGroup(existingHomework.getGroup());
        if (homeworkDto.getDeadline() == null) homeworkDto.setDeadline(existingHomework.getDeadline());

        return addHomework(homeworkDto);
    }

    public HomeworkDTO entityToDTO(@Valid Homework homework) {
        return null;
    }

    public List<HomeworkDTO> entityToDTO(List<@Valid Homework> homework) {
        return new ArrayList<>();
    }


    @Valid
    public Homework dtoToEntity(HomeworkDTO homeworkDto) {
        Homework entity = new Homework();
        entity.setId(homeworkDto.getId());
        entity.setName(homeworkDto.getName());
        entity.setDescription(homeworkDto.getDescription());
        entity.setSubject(homeworkDto.getSubject());
        entity.setGroupName(homeworkDto.getGroup());
        entity.setDeadline(homeworkDto.getDeadline());
        return entity;
    }
}
