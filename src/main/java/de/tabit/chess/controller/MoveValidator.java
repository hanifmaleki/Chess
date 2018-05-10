package de.tabit.chess.controller;

import de.tabit.chess.model.BoardStatus;
import de.tabit.chess.model.PiecePoint;

public interface MoveValidator {
    boolean moveValid(BoardStatus status, PiecePoint from, PiecePoint to);
}
