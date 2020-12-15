package com.c4u.course4you.exceptions;

public class DocumentStorageException  extends  RuntimeException{
    public DocumentStorageException() {
        super();
    }

    public DocumentStorageException(String message) {
        super(message);
    }

    public DocumentStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
