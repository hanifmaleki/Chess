package de.tabit.chess.controller;

import de.tabit.chess.model.BoardStatus;
import de.tabit.chess.model.PieceLocation;

/**
 * Created by e1528895 on 5/11/18.
 */
public class MoveValidatorImpl implements MoveValidator {

  @Override
  public boolean moveValid(BoardStatus status, PieceLocation from, PieceLocation to) {
    return true;
  }
}
