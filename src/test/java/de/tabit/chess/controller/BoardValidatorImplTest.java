package de.tabit.chess.controller;

import de.tabit.chess.controller.fileManager.BoardStatusFileManagerImpl;
import de.tabit.chess.controller.validators.BoardNotValidException;
import de.tabit.chess.controller.validators.BoardValidatorImpl;
import de.tabit.chess.controller.validators.MoveValidatorImpl;
import de.tabit.chess.model.BoardStatus;
import de.tabit.chess.model.Location;
import de.tabit.chess.model.PieceFactory;
import de.tabit.chess.model.PieceLocation;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/** Created by Hanif Maleki on 5/12/18. The aim is to test {@link BoardValidatorImpl } class */
@Category(MyCategory.class)
public class BoardValidatorImplTest {

  BoardController boardController;
  BoardValidatorImpl boardValidator;

  @Before
  public void init() {
    boardController = new BoardControllerImplementation();
    boardController.setMoveValidator(new MoveValidatorImpl());
    boardValidator = new BoardValidatorImpl();
    boardController.setBoardValidator(boardValidator);
    boardController.setFileManager(new BoardStatusFileManagerImpl());
  }

  @Test(expected = BoardNotValidException.class)
  public void validate1() throws BoardNotValidException {
    boardController.freshGame();
    PieceLocation pieceLocation =
        new PieceLocation(PieceFactory.getBlackPawn(), new Location(4, 4));
    BoardStatus newBoardStatus = boardController.getBoardStatus().union(pieceLocation);
    boardValidator.validate(newBoardStatus);
  }

  @Test
  public void validate2() {
    boardController.resetBorad();
    List<PieceLocation> pieceLocationList = new ArrayList<>();
    PieceLocation pieceLocation =
        new PieceLocation(PieceFactory.getWhiteKing(), Location.fromHumanReadable("a2"));
    pieceLocationList.add(pieceLocation);
    pieceLocation =
        new PieceLocation(PieceFactory.getBlackKing(), Location.fromHumanReadable("c3"));
    pieceLocationList.add(pieceLocation);
    BoardStatus newStatus = boardController.getBoardStatus().union(pieceLocationList);
    try {
      assert (boardValidator.validate(newStatus));
    } catch (BoardNotValidException e) {
      e.printStackTrace();
    }
  }

  @Test(expected = BoardNotValidException.class)
  public void validate3() throws BoardNotValidException {
    boardController.resetBorad();
    boardValidator.validate(boardController.getBoardStatus());
  }

  @Test(expected = BoardNotValidException.class)
  public void validate4() throws BoardNotValidException {
    boardController.freshGame();
    PieceLocation pieceLocation =
        new PieceLocation(PieceFactory.getBlackCastle(), Location.fromHumanReadable("a1"));
    BoardStatus newBoardStatus = boardController.getBoardStatus().union(pieceLocation);
    boardValidator.validate(newBoardStatus);
  }

  @Test(expected = BoardNotValidException.class)
  public void validate5() throws IOException, BoardNotValidException {
    boardController.makeBoardFromInputFile(new File("src/test/resources/failure-test.txt"));
  }

  @Test(expected = BoardNotValidException.class)
  public void validate6() throws IOException, BoardNotValidException {
    boardController.makeBoardFromInputFile(new File("src/test/resources/failure-test-2.txt"));
  }
}
