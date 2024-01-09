package com.hillel.multi.presentation.controllers;

import com.hillel.api.HomeworkManagerApi;
import com.hillel.model.Homework;
import com.hillel.multi.service.HomeworkManagerService;
import com.hillel.multi.service.utils.ListToResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeworkManagerController implements HomeworkManagerApi {

    @Autowired
    private HomeworkManagerService homeworkManagerService;

    @Override
    public ResponseEntity<List<Homework>> getHomework(String group, String subject) {
        List<Homework> homeworks = homeworkManagerService.getHomework(group, subject);
        return ListToResponseConverter.convert(homeworks);
    }

    @Override
    public ResponseEntity<Homework> addHomework(Homework homework) {
        Homework body = homeworkManagerService.addHomework(homework);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Homework> updateHomework(Integer id, Homework homework) {
        Homework body = homeworkManagerService.updateHomework(id, homework);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
