package com.c4u.course4you.exceptions;

public class FileNameExistException extends  RuntimeException {
    public FileNameExistException() {
        super();
    }

    public FileNameExistException(String message) {
        super(message);
    }

    public FileNameExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
