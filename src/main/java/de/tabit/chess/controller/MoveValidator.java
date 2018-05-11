package de.tabit.chess.controller;

import de.tabit.chess.model.BoardStatus;
import de.tabit.chess.model.PieceLocation;

public interface MoveValidator {
    boolean moveValid(BoardStatus status, PieceLocation from, PieceLocation to);
}
