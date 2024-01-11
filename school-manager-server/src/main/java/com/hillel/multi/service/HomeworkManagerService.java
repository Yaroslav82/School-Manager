package com.hillel.multi.service;

import com.hillel.model.Homework;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HomeworkManagerService {

    public List<Homework> getHomework(String group, String subject) {
        return null;
    }

    public Homework addHomework(Homework homework) {
        // Example of using exception
        if (Objects.isNull(homework.getName())) {
            throw new MediaTypeException("Homework name can not be null");
        }

        return homework;
    }

    public Homework updateHomework(Integer id, Homework homework) {
        // Example of using exception
        if (id < 0) {
            throw new NotFoundException("Homework with id " + id + " is not found.");
        }

        return homework;
    }
}
