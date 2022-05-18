package com.project.freelance.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BadRequestException.class, NoSuchFieldException.class, NumberFormatException.class, JsonProcessingException.class, IllegalArgumentException.class, PropertyReferenceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse runtime(RuntimeException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidRequestException(InvalidRequestException invalidRequestException) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), invalidRequestException.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundHandler(NotFoundException notFoundException) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), notFoundException.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse conflictHandler(ConflictException conflictException) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), conflictException.getMessage());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse httpClientErrorHandler(HttpClientErrorException httpClientErrorException) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), httpClientErrorException.getMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ErrorResponse handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return new ErrorResponse(HttpStatus.EXPECTATION_FAILED.value(), exc.getMessage());
    }
}
