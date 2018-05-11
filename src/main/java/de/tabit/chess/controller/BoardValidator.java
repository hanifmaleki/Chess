package de.tabit.chess.controller;

import de.tabit.chess.model.BoardStatus;

public interface BoardValidator {
  boolean validate(BoardStatus boardStatus) throws BoardNotValidException;
}
