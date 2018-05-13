package de.tabit.chess.controller.validators;

import de.tabit.chess.model.BoardStatus;
import de.tabit.chess.model.PieceLocation;

/**
 *  This interface enables checking the validity of the user moves
 */
public interface MoveValidator {
    boolean moveValid(BoardStatus status, PieceLocation from, PieceLocation to);
}
