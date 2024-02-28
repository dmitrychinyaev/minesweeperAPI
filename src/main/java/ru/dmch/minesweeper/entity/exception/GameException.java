package ru.dmch.minesweeper.entity.exception;

public class GameException extends RuntimeException {
    private String message;

    public GameException() {
    }

    public GameException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
