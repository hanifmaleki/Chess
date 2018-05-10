package de.tabit.chess.controller;

public class BoardNotValidException extends Exception {

    public BoardNotValidException() {
    }

    public BoardNotValidException(String message) {
        super(message);
    }

    public BoardNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoardNotValidException(Throwable cause) {
        super(cause);
    }
}
