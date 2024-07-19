package com.os.service.api.exceptionhandler;

import com.os.service.domain.exception.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestControllerAdvice
 class GlobalException extends ResponseEntityExceptionHandler {

    private String timestamp = LocalDateTime.now().toString();

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatusCode.valueOf(400));
        problem.setTitle("Error validating the fields entered");
        problem.setDetail("One or more fields are invalid. Fill in correctly and try again.");
        problem.setProperty("timestamp", Instant.now());

        Map<String, String> errors = getErrorFields(ex);
        errors.forEach((fieldName, message) -> {
            problem.setProperty(fieldName, message);
        });

        log.error("[{}] - [GlobalExeption] - MethodArgumentNotValidException: Error validating the fields entered", timestamp);
        errors.forEach((fieldName, message) -> {
            log.error("[{}] - [GlobalExeption] - Invalid field: {} - Message: {}", timestamp, fieldName, message);
        });

        return new ResponseEntity<Object>(problem, status);
    }

    private Map<String, String> getErrorFields(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }

    @ExceptionHandler(GroupServiceNotFoundException.class)
    ProblemDetail handlerGroupServiceNotFoundException(GroupServiceNotFoundException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());

        problem.setTitle("Unregistered group");
        problem.setProperty("timestamp", Instant.now());

        log.error("[{}] - [GlobalExeption] - GroupServiceNotFoundException: {}", timestamp, e.getMessage());
        return problem;

    }

    @ExceptionHandler(ServiceNotFoundException.class)
    ProblemDetail handlerServiceNotFoundException(ServiceNotFoundException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());

        problem.setTitle("Unregistered service");
        problem.setProperty("timestamp", Instant.now());

        log.error("[{}] - [GlobalExeption] - ServiceNotFoundException: {}", timestamp, e.getMessage());
        return problem;

    }

    @ExceptionHandler(ServiceInOrderNotFoundException.class)
    ProblemDetail handlerServiceInOrderNotFoundException(ServiceInOrderNotFoundException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());

        problem.setTitle("Unregistered service");
        problem.setProperty("timestamp", Instant.now());

        log.error("[{}] - [GlobalExeption] - ServiceInOrderNotFoundException: {}", timestamp, e.getMessage());
        return problem;

    }


    @ExceptionHandler(OrderNotFoundException.class)
    ProblemDetail handlerOrderNotFoundException(OrderNotFoundException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());

        problem.setTitle("Unregistered order");
        problem.setProperty("timestamp", Instant.now());

        log.error("[{}] - [GlobalExeption] - OrderNotFoundException: {}", timestamp, e.getMessage());
        return problem;

    }

    @ExceptionHandler(OrderFinishedOrCanceledException.class)
    ProblemDetail handlerOrderFinishedOrCanceledException(OrderFinishedOrCanceledException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());

        problem.setTitle("Violation of business rules.");
        problem.setProperty("timestamp", Instant.now());

        log.error("[{}] - [GlobalExeption] - OrderFinishedOrCanceledException: {}", timestamp, e.getMessage());
        return problem;

    }

    @ExceptionHandler(OrderWrongStatusException.class)
    ProblemDetail handlerOrderWrongStatusException(OrderWrongStatusException e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());

        problem.setTitle("Violation of business rules.");
        problem.setProperty("timestamp", Instant.now());

        log.error("[{}] - [GlobalExeption] - OrderWrongStatusException: {}", timestamp, e.getMessage());
        return problem;

    }
    @ExceptionHandler(ErrorUploadPhotoForS3Exception.class)
    ProblemDetail handlerErrorUploadPhotoForS3Exception(ErrorUploadPhotoForS3Exception e) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        problem.setTitle("Error serializing or saving photo.");
        problem.setProperty("timestamp", Instant.now());

        log.error("[{}] - [GlobalExeption] - ErrorUploadPhotoForS3Exception: {}", timestamp, e.getMessage());
        return problem;

    }


//        @ExceptionHandler(InformationInUseException.class)
//        ProblemDetail handlerInformationInUseException(InformationInUseException e) {
//            ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
//
//            problem.setTitle("Violation of business rules.");
//            problem.setProperty("timestamp", Instant.now());
//
//            log.error("[{}] - [GlobalExeption] - InformationInUseException: {}", timestamp, e.getMessage());
//            return problem;
//
//        }
}

