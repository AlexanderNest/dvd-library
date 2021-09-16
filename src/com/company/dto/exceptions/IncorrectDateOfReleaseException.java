package com.company.dto.exceptions;

import java.time.DateTimeException;

public class IncorrectDateOfReleaseException extends DateTimeException {
    public IncorrectDateOfReleaseException(String message) {
        super(message);
    }
}
