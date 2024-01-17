package com.hillel.multi.service;

import com.hillel.model.HomeworkModel;
import com.hillel.multi.configuration.exceptions.MediaTypeException;
import com.hillel.multi.configuration.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HomeworkManagerService {

    public List<HomeworkModel> getHomework(String group, String subject) {
        return null;
    }

    public HomeworkModel addHomework(HomeworkModel homeworkModel) {
        // Example of using exception
        if (Objects.isNull(homeworkModel.getName())) {
            throw new MediaTypeException("Homework name can not be null");
        }

        return homeworkModel;
    }

    public HomeworkModel updateHomework(Integer id, HomeworkModel homeworkModel) {
        // Example of using exception
        if (id < 0) {
            throw new NotFoundException("Homework with id " + id + " is not found.");
        }

        return homeworkModel;
    }
}
