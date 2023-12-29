package com.hillel.multi.service.utils;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public class ListToResponseConverter {

    public static <T> ResponseEntity<List<T>> convert(List<T> entityList) {
        if (Objects.isNull(entityList)) {
            return ResponseEntity.internalServerError().build();
        } else if (entityList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(entityList);
        }
    }
}
