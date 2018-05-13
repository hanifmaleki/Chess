package de.tabit.chess.controller.validators;

/** The exception of wrong number of pieces in the board. */
public class BoardNotValidException extends Exception {

  public BoardNotValidException() {}

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
