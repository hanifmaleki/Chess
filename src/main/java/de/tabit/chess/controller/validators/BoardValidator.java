package de.tabit.chess.controller.validators;

import de.tabit.chess.model.BoardStatus;

/**
 * The interface of checking the validation of loading {BoardStatus} in to the current chess board.
 */
public interface BoardValidator {
  boolean validate(BoardStatus boardStatus) throws BoardNotValidException;
}
