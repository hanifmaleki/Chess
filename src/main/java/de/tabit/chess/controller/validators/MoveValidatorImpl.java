package de.tabit.chess.controller.validators;

import de.tabit.chess.model.BoardStatus;
import de.tabit.chess.model.PieceLocation;

/**
 * Created by Hanif Maleki on 5/11/18. The implementation of the {{@link MoveValidator}}. Since in
 * the current version move validation is not necessary, are moves are alowed in the current
 * implementation.
 */
public class MoveValidatorImpl implements MoveValidator {

  @Override
  public boolean moveValid(BoardStatus status, PieceLocation from, PieceLocation to) {
    return true;
  }
}
