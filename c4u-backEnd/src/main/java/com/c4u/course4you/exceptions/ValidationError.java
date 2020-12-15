package com.c4u.course4you.exceptions;

import lombok.Data;

@Data
public class ValidationError {
    private String code;
    private String message;
}
