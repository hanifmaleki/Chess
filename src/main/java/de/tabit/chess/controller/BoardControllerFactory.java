package de.tabit.chess.controller;

import de.tabit.chess.controller.fileManager.BoardStatusFileManagerImpl;
import de.tabit.chess.controller.validators.BoardValidatorImpl;
import de.tabit.chess.controller.validators.MoveValidatorImpl;

/**
 * Created by Hanif Maleki on 5/13/18. create a default BoardController with default {@link
 * de.tabit.chess.controller.validators.BoardValidator}, default {@link
 * de.tabit.chess.controller.validators.MoveValidator}, and default {@link
 * de.tabit.chess.controller.fileManager.BoardStatusFileManager}
 */
public class BoardControllerFactory {

  public static BoardController getDefaultBoardController() {
    BoardController boardController = new BoardControllerImplementation();
    boardController.setBoardValidator(new BoardValidatorImpl());
    boardController.setMoveValidator(new MoveValidatorImpl());
    boardController.setFileManager(new BoardStatusFileManagerImpl());
    return boardController;
  }
}
