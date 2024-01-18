package com.hillel.multi.service;

import com.hillel.model.HomeworkDTO;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import com.hillel.multi.persistent.entities.Homework;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
@Validated
public class HomeworkManagerService {

    public List<HomeworkDTO> getHomework(String group, String subject) {
        return null;
    }

    public HomeworkDTO addHomework(HomeworkDTO homeworkDto) {
        // Example of using exception
        if (Objects.isNull(homeworkDto.getName())) {
            throw new MediaTypeException("Homework name can not be null");
        }

        return homeworkDto;
    }

    public HomeworkDTO updateHomework(Integer id, HomeworkDTO homeworkDto) {
        // Example of using exception
        if (id < 0) {
            throw new NotFoundException("Homework with id " + id + " is not found.");
        }

        return homeworkDto;
    }

    public HomeworkDTO entityToDTO(@Valid Homework homework) {
        return null;
    }

    public Homework entityToDTO(HomeworkDTO homeworkDTO) {
        return null;
    }
}
