package ru.practicum.explore.error;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.rmi.AccessException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleEntityNotFoundException(final EntityNotFoundException e) {
        String errorMessage;
        if (e.getMessage().contains("ru")) {
            String[] message = e.getMessage().split("\\.");
            String firstLine = message[0].substring(0, message[0].length() - 2);
            String secondLine = message[5];
            errorMessage = firstLine + secondLine;
        } else {
            errorMessage = e.getMessage();
        }

        return new ApiError(
                HttpStatus.NOT_FOUND.toString(),
                errorMessage,
                "The required object was not found.",
                LocalDateTime.now()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleIllegalArgumentException(final IllegalArgumentException e) {
        return new ApiError(
                HttpStatus.BAD_REQUEST.toString(),
                e.getMessage(),
                "For the requested operation the conditions are not met.",
                LocalDateTime.now()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleEmptyResultException(final EmptyResultDataAccessException e) {
        String errorMessage = "Unable to find " + e.getMessage().split(" ")[2].split("\\.")[5] + " id " + e.getMessage().split(" ")[6];

        return new ApiError(
                HttpStatus.NOT_FOUND.name(),
                errorMessage,
                "The required object was not found.",
                LocalDateTime.now()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleSqlException(final PSQLException e) {
        return new ApiError(
                HttpStatus.CONFLICT.name(),
                e.getMessage(),
                "Integrity constraint has been violated",
                LocalDateTime.now()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleIntegrityException(final DataIntegrityViolationException e) {
        return new ApiError(
                HttpStatus.CONFLICT.name(),
                e.getMessage(),
                "Integrity constraint has been violated",
                LocalDateTime.now()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    public ApiError handleAccessException(final AccessException e) {
        return new ApiError(
                HttpStatus.NON_AUTHORITATIVE_INFORMATION.name(),
                e.getMessage(),
                "For the requested operation the conditions are not met.",
                LocalDateTime.now()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleIllegalStateException(final IllegalStateException e) {
        return new ApiError(
                HttpStatus.BAD_REQUEST.name(),
                e.getMessage(),
                "For the requested operation the conditions are not met.",
                LocalDateTime.now()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleIllegalAccessException(final IllegalAccessException e) {
        return new ApiError(
                HttpStatus.BAD_REQUEST.name(),
                e.getMessage(),
                "For the requested operation the conditions are not met.",
                LocalDateTime.now()
        );
    }
}
