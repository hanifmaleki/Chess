package de.tabit.chess.controller;

import de.tabit.chess.controller.fileManager.BoardStatusFileManager;
import de.tabit.chess.controller.validators.BoardNotValidException;
import de.tabit.chess.controller.validators.BoardValidator;
import de.tabit.chess.controller.validators.MoveValidator;
import de.tabit.chess.model.BoardStatus;
import de.tabit.chess.model.PieceFactory;
import de.tabit.chess.model.PieceLocation;
import de.tabit.chess.view.BoardStatusChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/** The implementation of BoardController */
@Slf4j
public class BoardControllerImplementation implements BoardController {
  private @Setter @Getter BoardStatus boardStatus;
  private @Setter @Getter
  BoardValidator boardValidator;
  private @Setter @Getter
  MoveValidator moveValidator;
  private @Setter @Getter BoardStatusChangeListener changeListener;
  private @Setter @Getter
  BoardStatusFileManager fileManager;

  public BoardControllerImplementation() {
    boardStatus = new BoardStatus();
  }

  @Override
  public void addPieceToBoard(PieceLocation pieceLocation) throws BoardNotValidException {
    log.debug("Adding " + pieceLocation + " to the board.");
    BoardStatus newBoardStatus = boardStatus.union(pieceLocation);
    if (boardValidator.validate(newBoardStatus)) boardStatus = newBoardStatus;
  }

  @Override
  public void addPiecesToBoard(java.util.List<PieceLocation> pieceLocations)
      throws BoardNotValidException {
    log.debug("Adding a list of size " + pieceLocations.size() + " to the board");
    BoardStatus newBoardStatus = boardStatus.union(pieceLocations);
    if (boardValidator.validate(newBoardStatus)) boardStatus = newBoardStatus;
  }

  @Override
  public java.util.List<PieceLocation> getCurrentPieces() {
    return boardStatus.getCurrentPieces();
  }

  @Override
  public void freshGame() {
    log.debug("Arranging a new game");
    clearBoard();
    try {
      List<PieceLocation> pieces = PieceFactory.getWhitePices();
      pieces.addAll(PieceFactory.getBlackPices());
      addPiecesToBoard(pieces);
    } catch (BoardNotValidException e) {
      log.error(e.getMessage());
    }
    if (changeListener != null) changeListener.statusChanged(boardStatus);
  }

  @Override
  public void saveBoradToFile(File selectedFile) throws IOException {
    fileManager.writeToFile(boardStatus, selectedFile);
  }

  @Override
  public void makeBoardFromInputFile(File selectedFile) throws BoardNotValidException, IOException {
    log.debug("Reading the board from file " + selectedFile.getName());
    BoardStatus status = fileManager.readFromFile(selectedFile);
    boardValidator.validate(status);
    boardStatus = status;
    if (changeListener != null) changeListener.statusChanged(this.boardStatus);
  }

  @Override
  public void resetBorad() {
    log.debug("Resetting the board");
    clearBoard();
    if (changeListener != null) changeListener.statusChanged(boardStatus);
  }

  private void clearBoard() {
    boardStatus = new BoardStatus();
  }

  @Override
  public boolean makeAMove(PieceLocation from, PieceLocation to) {
    log.debug("Checking the validity of move " + from + "-" + to);
    boolean moveIsValid = moveValidator.moveValid(boardStatus, from, to);
    if (moveIsValid) boardStatus.makeMove(from, to);
    return moveIsValid;
  }
}
