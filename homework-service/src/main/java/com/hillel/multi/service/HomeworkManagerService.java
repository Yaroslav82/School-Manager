package com.hillel.multi.service;

import com.hillel.model.HomeworkDTO;
import com.hillel.multi.persistent.entities.Homework;
import com.hillel.multi.persistent.repositories.HomeworkManagerRepository;
import com.hillel.multi.service.mappers.HomeworkMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Validated
@AllArgsConstructor
public class HomeworkManagerService {

    private HomeworkManagerRepository homeworkManagerRepository;

    public List<HomeworkDTO> getHomework(String group, String subject) {
        log.info("Getting homework for group '{}' and subject '{}'", group, subject);
        List<Homework> homeworkList = homeworkManagerRepository.getHomeworkByGroupAndSubject(group, subject);
        return entityToDTO(homeworkList);
    }

    public HomeworkDTO addHomework(HomeworkDTO homeworkDto) {
        Homework entity = homeworkManagerRepository.save(dtoToEntity(homeworkDto));
        log.info("Homework with id '{}' added", entity.getId());
        return entityToDTO(entity);
    }

    public HomeworkDTO updateHomework(Integer id, HomeworkDTO homeworkDto) {
        Homework entity = getEntityById(id);
        HomeworkMapper.INSTANCE.updateIntoHomework(entity, homeworkDto);
        entity = homeworkManagerRepository.save(entity);
        log.info("Homework with id '{}' updated", id);
        return entityToDTO(entity);
    }

    public void deleteHomework(Integer id) {
        Homework entity = getEntityById(id);
        homeworkManagerRepository.delete(entity);
        log.info("Homework with id '{}' was deleted", id);
    }

    private HomeworkDTO entityToDTO(@Valid Homework homework) {
        return HomeworkMapper.INSTANCE.homeworkToDto(homework);
    }

    private List<HomeworkDTO> entityToDTO(List<@Valid Homework> homework) {
        return HomeworkMapper.INSTANCE.homeworksToDto(homework);
    }

    @Valid
    private Homework dtoToEntity(HomeworkDTO homeworkDto) {
        return HomeworkMapper.INSTANCE.dtoToHomework(homeworkDto);
    }

    private Homework getEntityById(Integer id) {
        Optional<Homework> entity = homeworkManagerRepository.getHomeworkById(id.longValue());
        return entity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Homework with id " + id + " not found"));
    }
}
