package com.hillel.multi.presentation.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {

    private Date timestamp;
    private Integer status;
    private HashMap<String, String> errors;
}