package com.rookies.assignment.exceptions.handlers;

import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ResourceFoundException.class})
    protected ResponseDto handleResourceNotFoundException(RuntimeException exception,
                                                          WebRequest request) {
        return new ResponseDto("404", exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalArgumentException.class, RepeatDataException.class})
    protected ResponseDto handleIllegalArgumentException(RuntimeException exception) {
        return new ResponseDto("400", exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseDto handleException(RuntimeException exception,
                                          WebRequest request) {
        return new ResponseDto("500", "Unknow error", null);

    }

    @ExceptionHandler({ParamNotValidException.class})
    protected ResponseDto handleParamNotValidException(RuntimeException exception) {
        return new ResponseDto("499", exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    lỗi tài nguyên đang tạm khóa.
    @ExceptionHandler({LockedException.class})
    protected ResponseDto handleFailedDependencyException(RuntimeException exception) {
        return new ResponseDto("423", exception.getMessage(), HttpStatus.LOCKED);
    }

//    The user has sent too many requests in a given amount of time
    @ExceptionHandler({TooManyRequestsException.class})
    protected ResponseDto handleTooManyRequestsException(RuntimeException exception) {
        return new ResponseDto("429", exception.getMessage(), HttpStatus.LOCKED);
    }

//    The request method is known by the server but is not supported by the target resource
    @ExceptionHandler({MethodNotAllowedException.class})
    protected ResponseDto handleMethodNotAllowedException(RuntimeException exception) {
        return new ResponseDto("405", exception.getMessage(), HttpStatus.LOCKED);
    }


    public GlobalExceptionsHandler() {
        super();
    }


}
