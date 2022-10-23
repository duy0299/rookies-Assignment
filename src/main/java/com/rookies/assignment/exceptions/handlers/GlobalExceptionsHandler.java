package com.rookies.assignment.exceptions.handlers;

import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.Null;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ResourceFoundException.class})
    protected ResponseEntity handleResourceNotFoundException(RuntimeException exception,
                                                          WebRequest request) {
        ResponseDto error =  new ResponseDto("404", exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<ResponseDto>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ RepeatDataException.class})
    protected ResponseEntity handleRepeatDataException(RuntimeException exception) {
        ResponseDto error = new ResponseDto("400", exception.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<ResponseDto>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    protected ResponseEntity handleIllegalArgumentException(RuntimeException exception) {
        ResponseDto error = new ResponseDto("400", exception.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<ResponseDto>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleException(RuntimeException exception,
                                          WebRequest request) {
        ResponseDto error = new ResponseDto("500", exception.getMessage(), HttpStatus.valueOf(500));
        return new ResponseEntity<ResponseDto>(error, HttpStatus.valueOf(500));
    }

    @ExceptionHandler({ParamNotValidException.class})
    protected ResponseEntity handleParamNotValidException(RuntimeException exception) {
        ResponseDto error = new ResponseDto("499", exception.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<ResponseDto>(error, HttpStatus.BAD_REQUEST);
    }

//    lỗi tài nguyên đang tạm khóa.
    @ExceptionHandler({LockedException.class})
    protected ResponseEntity handleFailedDependencyException(RuntimeException exception) {
        ResponseDto error = new ResponseDto("423", exception.getMessage(), HttpStatus.LOCKED);
        return new ResponseEntity<ResponseDto>(error, HttpStatus.LOCKED);
    }

//    The user has sent too many requests in a given amount of time
    @ExceptionHandler({TooManyRequestsException.class})
    protected ResponseEntity handleTooManyRequestsException(RuntimeException exception) {
        ResponseDto error = new ResponseDto("429", exception.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
        return new ResponseEntity<ResponseDto>(error, HttpStatus.TOO_MANY_REQUESTS);
    }

//    The request method is known by the server but is not supported by the target resource
    @ExceptionHandler({MethodNotAllowedException.class})
    protected ResponseEntity handleMethodNotAllowedException(RuntimeException exception) {
        ResponseDto error = new ResponseDto("405", exception.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        return new ResponseEntity<ResponseDto>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({ForbiddenException.class})
    protected ResponseEntity handleForbiddenException(RuntimeException exception) {
        ResponseDto error = new ResponseDto("403", exception.getMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<ResponseDto>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({NullPointerException.class})
    protected ResponseEntity handleNullPointerException(RuntimeException exception) {
        ResponseDto error = new ResponseDto("498", exception.getMessage(), "NullPointerException");
        return new ResponseEntity<ResponseDto>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({IOException.class})
    protected ResponseEntity handleIOException(RuntimeException exception) {
        ResponseDto error = new ResponseDto("497", exception.getMessage(), "IOException");
        return new ResponseEntity<ResponseDto>(error, HttpStatus.NOT_MODIFIED);
    }

    public GlobalExceptionsHandler() {
        super();
    }


}
