package ru.dmch.minesweeper.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.dmch.minesweeper.entity.exception.GameException;
import ru.dmch.minesweeper.entity.response.ErrorResponse;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(GameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse processValidationError(GameException se) {
        ErrorResponse response = new ErrorResponse(se.getMessage());
        return response;
    }
}
