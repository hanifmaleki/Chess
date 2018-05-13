package de.tabit.chess.controller;

import de.tabit.chess.controller.fileManager.BoardStatusFileManager;
import de.tabit.chess.controller.validators.BoardNotValidException;
import de.tabit.chess.controller.validators.BoardValidator;
import de.tabit.chess.controller.validators.MoveValidator;
import de.tabit.chess.model.BoardStatus;
import de.tabit.chess.model.PieceLocation;
import de.tabit.chess.view.BoardStatusChangeListener;
import java.io.File;
import java.io.IOException;

/** Created by Hanif Maleki on 5/11/18. This interface defines the main features of the library */
public interface BoardController {

  void addPieceToBoard(PieceLocation pieceLocation) throws BoardNotValidException;

  void addPiecesToBoard(java.util.List<PieceLocation> pieceLocations) throws BoardNotValidException;

  java.util.List<PieceLocation> getCurrentPieces();

  void freshGame();

  void saveBoradToFile(File selectedFile) throws IOException;

  void makeBoardFromInputFile(File selectedFile) throws BoardNotValidException, IOException;

  void resetBorad();

  boolean makeAMove(PieceLocation from, PieceLocation to);

  BoardValidator getBoardValidator();

  void setBoardValidator(BoardValidator boardValidator);

  MoveValidator getMoveValidator();

  void setMoveValidator(MoveValidator moveValidator);

  BoardStatusChangeListener getChangeListener();

  void setChangeListener(BoardStatusChangeListener changeListener);

  BoardStatus getBoardStatus();

  void setBoardStatus(BoardStatus boardStatus);

  BoardStatusFileManager getFileManager();

  void setFileManager(BoardStatusFileManager manager);
}
