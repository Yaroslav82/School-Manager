package com.hillel.multi.presentation.controllers;

import com.hillel.api.HomeworkManagerApi;
import com.hillel.model.HomeworkDTO;
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
    public ResponseEntity<List<HomeworkDTO>> getHomework(String group, String subject) {
        List<HomeworkDTO> homeworkDTOS = homeworkManagerService.getHomework(group, subject);
        return new ResponseEntity<>(homeworkDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HomeworkDTO> addHomework(HomeworkDTO homeworkDto) {
        HomeworkDTO body = homeworkManagerService.addHomework(homeworkDto);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HomeworkDTO> updateHomework(Integer id, HomeworkDTO homeworkDto) {
        HomeworkDTO body = homeworkManagerService.updateHomework(id, homeworkDto);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
