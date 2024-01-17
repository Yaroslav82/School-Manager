package com.hillel.multi.presentation.controllers;

import com.hillel.api.HomeworkManagerApi;
import com.hillel.model.HomeworkModel;
import com.hillel.multi.service.HomeworkManagerService;
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
    public ResponseEntity<List<HomeworkModel>> getHomework(String group, String subject) {
        List<HomeworkModel> homeworkModels = homeworkManagerService.getHomework(group, subject);
        return new ResponseEntity<>(homeworkModels, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HomeworkModel> addHomework(HomeworkModel homeworkModel) {
        HomeworkModel body = homeworkManagerService.addHomework(homeworkModel);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HomeworkModel> updateHomework(Integer id, HomeworkModel homeworkModel) {
        HomeworkModel body = homeworkManagerService.updateHomework(id, homeworkModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
