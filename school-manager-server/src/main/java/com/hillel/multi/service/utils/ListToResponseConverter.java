package com.hillel.multi.service.utils;

import com.hillel.multi.configuration.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public class ListToResponseConverter {

    public static <T> ResponseEntity<List<T>> convert(List<T> entityList) {
        if (Objects.isNull(entityList)) {
            throw new NotFoundException();
        } else {
            return ResponseEntity.ok(entityList);
        }
    }
}
