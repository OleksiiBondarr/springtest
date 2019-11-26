package spring.mvc.roombooking.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionsAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PasswordIsToShortException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String passwordIsToShortHandler(PasswordIsToShortException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String userAlreadyExistHandler(UserAlreadyExistException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RoomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String roomNotFoundHandler(RoomNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RoomAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String roomAlreadyExistHandler(RoomAlreadyExistException ex) {
        return ex.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(RoomIsNotAvailableException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String RoomIsNotAvailableHandler(RoomIsNotAvailableException ex) {
        return ex.getMessage();
    }

}