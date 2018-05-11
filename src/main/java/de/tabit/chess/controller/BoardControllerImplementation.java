package de.tabit.chess.controller;

import de.tabit.chess.model.BoardStatus;
import de.tabit.chess.model.PieceFactory;
import de.tabit.chess.model.PieceLocation;
import de.tabit.chess.view.BoardStatusChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class BoardControllerImplementation implements BoardController {
  private BoardStatus boardStatus;
  private BoardValidator boardValidator;
  private MoveValidator moveValidator;
  private BoardStatusChangeListener changeListener;
  private BoardStatusFileManager fileManager;

  public BoardControllerImplementation() {
    boardStatus = new BoardStatus();
  }

  @Override
  public void addPieceToBoard(PieceLocation pieceLocation) throws BoardNotValidException {
    BoardStatus newBoardStatus = boardStatus.union(pieceLocation);
    if (boardValidator.validate(newBoardStatus)) boardStatus = newBoardStatus;
  }

  @Override
  public void addPiecesToBoard(java.util.List<PieceLocation> pieceLocations)
      throws BoardNotValidException {
    // piecePoints.forEach(p->addPieceToBoard(p.getPiece(),p.getPoint()));
    BoardStatus newBoardStatus = boardStatus.union(pieceLocations);
    if (boardValidator.validate(newBoardStatus)) boardStatus = newBoardStatus;
  }

  @Override
  public java.util.List<PieceLocation> getCurrentPieces() {
    return boardStatus.getCurrentPieces();
  }

  @Override
  public void freshGame() {
    clearBoard();
    try {
      List<PieceLocation> pieces = PieceFactory.getWhitePices();
      pieces.addAll(PieceFactory.getBlackPices());
      addPiecesToBoard(pieces);
    } catch (BoardNotValidException e) {
      // TODO there can not be an exception here
      e.printStackTrace();
    }
    if(changeListener!=null)
    changeListener.statusChanged(boardStatus);
  }

  // TODO do something about error handling
  @Override
  public void saveBoradToFile(File selectedFile) throws IOException {
    fileManager.writeToFile(boardStatus, selectedFile);
  }

  // TODO something more about exception handling
  @Override
  public void makeBoardFromInputFile(File selectedFile)
      throws BoardNotValidException, FileNotFoundException, UnsupportedEncodingException {
    BoardStatus status = fileManager.readFromFile(selectedFile);
    boardValidator.validate(status);
    boardStatus = status;
    if(changeListener!=null)
      changeListener.statusChanged(this.boardStatus);
  }

  @Override
  public void resetBorad() {
    clearBoard();
    if(changeListener!=null)
      changeListener.statusChanged(boardStatus);
  }

  private void clearBoard() {
    boardStatus = new BoardStatus();
  }

  @Override
  public boolean makeAMove(PieceLocation from, PieceLocation to) {
    boolean b = moveValidator.moveValid(boardStatus, from, to);
    boardStatus.makeMove(from, to);
    return b;
  }

  // TODO enable lombok
  @Override
  public BoardValidator getBoardValidator() {
    return boardValidator;
  }

  @Override
  public void setBoardValidator(BoardValidator boardValidator) {
    this.boardValidator = boardValidator;
  }

  @Override
  public MoveValidator getMoveValidator() {
    return moveValidator;
  }

  @Override
  public void setMoveValidator(MoveValidator moveValidator) {
    this.moveValidator = moveValidator;
  }

  @Override
  public BoardStatusChangeListener getChangeListener() {
    return changeListener;
  }

  @Override
  public void setChangeListener(BoardStatusChangeListener changeListener) {
    this.changeListener = changeListener;
  }

  @Override
  public BoardStatus getBoardStatus() {
    return boardStatus;
  }

  @Override
  public void setBoardStatus(BoardStatus boardStatus) {
    this.boardStatus = boardStatus;
  }

  @Override
  public BoardStatusFileManager getFileManager() {
    return fileManager;
  }

  @Override
  public void setFileManager(BoardStatusFileManager manager) {
    this.fileManager = manager;
  }
}
