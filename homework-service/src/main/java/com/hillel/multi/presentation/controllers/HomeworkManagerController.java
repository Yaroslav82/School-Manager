package com.hillel.multi.presentation.controllers;

import com.hillel.api.HomeworkManagerApi;
import com.hillel.model.HomeworkDTO;
import com.hillel.multi.service.HomeworkManagerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class HomeworkManagerController implements HomeworkManagerApi {

    private HomeworkManagerService homeworkManagerService;

    @Override
    public ResponseEntity<List<HomeworkDTO>> getHomework(String group, String subject) {
        log.info("Received request to get homework for group '{}' and subject '{}'", group, subject);
        List<HomeworkDTO> homeworkDTOS = homeworkManagerService.getHomework(group, subject);
        return new ResponseEntity<>(homeworkDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HomeworkDTO> addHomework(HomeworkDTO homeworkDto) {
        log.info("Received request to add homework");
        HomeworkDTO body = homeworkManagerService.addHomework(homeworkDto);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HomeworkDTO> updateHomework(Integer id, HomeworkDTO homeworkDto) {
        log.info("Received request to update homework with id '{}'", id);
        HomeworkDTO body = homeworkManagerService.updateHomework(id, homeworkDto);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteHomework(Integer id) {
        log.info("Received request to delete homework with id '{}'", id);
        homeworkManagerService.deleteHomework(id);
        return ResponseEntity.noContent().build();
    }
}
