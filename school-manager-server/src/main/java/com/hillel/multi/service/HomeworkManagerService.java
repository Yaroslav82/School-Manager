package com.hillel.multi.service;

import com.hillel.model.Homework;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkManagerService {

    public List<Homework> getHomework(String group, String subject) {
        return null;
    }

    public ResponseEntity<Homework> addHomework(Homework homework) {
        return new ResponseEntity<>(homework, HttpStatus.CREATED);
    }

    public ResponseEntity<Homework> updateHomework(Integer id, Homework homework) {
        return new ResponseEntity<>(homework, HttpStatus.OK);
    }
}
