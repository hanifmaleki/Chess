package de.tabit.chess.controller;

import static org.junit.Assert.assertEquals;

import de.tabit.chess.controller.validators.BoardValidatorImpl;
import de.tabit.chess.controller.validators.MoveValidatorImpl;
import de.tabit.chess.model.Location;
import de.tabit.chess.model.Piece.Side;
import de.tabit.chess.model.PieceFactory;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Created by Hanif Maleki on 5/11/18. The class goal is to test {@link
 * BoardControllerImplementation} class
 */
@Category(MyCategory.class)
public class BoardControllerImplementationTest {

  BoardController boardController;

  @Before
  public void init() {
    boardController = new BoardControllerImplementation();
    boardController.setMoveValidator(new MoveValidatorImpl());
    boardController.setBoardValidator(new BoardValidatorImpl());
  }

  @Test
  public void freshGame() throws Exception {

    boardController.freshGame();
    assertEquals(boardController.getCurrentPieces().size(), 32);
    for (Side side : Side.values()) {
      assertEquals(boardController.getBoardStatus().getNumberOf(PieceFactory.getKing(side)), 1);
      assertEquals(boardController.getBoardStatus().getNumberOf(PieceFactory.getQueen(side)), 1);
      assertEquals(boardController.getBoardStatus().getNumberOf(PieceFactory.getBishop(side)), 2);
      assertEquals(boardController.getBoardStatus().getNumberOf(PieceFactory.getKnight(side)), 2);
      assertEquals(boardController.getBoardStatus().getNumberOf(PieceFactory.getCastle(side)), 2);
    }

    List<Location> locations =
        boardController.getBoardStatus().getLocationOf(PieceFactory.getWhiteKing());
    assertEquals(locations.get(0), Location.fromHumanReadable("e1"));
    locations = boardController.getBoardStatus().getLocationOf(PieceFactory.getBlackKing());
    assertEquals(locations.get(0), Location.fromHumanReadable("e8"));

    locations = boardController.getBoardStatus().getLocationOf(PieceFactory.getWhiteQueen());
    assertEquals(locations.get(0), Location.fromHumanReadable("d1"));
    locations = boardController.getBoardStatus().getLocationOf(PieceFactory.getBlackQueen());
    assertEquals(locations.get(0), Location.fromHumanReadable("d8"));

    locations = boardController.getBoardStatus().getLocationOf(PieceFactory.getWhiteCastle());
    assert (locations.contains(Location.fromHumanReadable("a1")));
    assert (locations.contains(Location.fromHumanReadable("h1")));

    locations = boardController.getBoardStatus().getLocationOf(PieceFactory.getBlackCastle());
    assert (locations.contains(Location.fromHumanReadable("a8")));
    assert (locations.contains(Location.fromHumanReadable("h8")));

    locations = boardController.getBoardStatus().getLocationOf(PieceFactory.getWhiteBishop());
    assert (locations.contains(Location.fromHumanReadable("c1")));
    assert (locations.contains(Location.fromHumanReadable("f1")));

    locations = boardController.getBoardStatus().getLocationOf(PieceFactory.getBlackBishop());
    assert (locations.contains(Location.fromHumanReadable("c8")));
    assert (locations.contains(Location.fromHumanReadable("f8")));

    locations = boardController.getBoardStatus().getLocationOf(PieceFactory.getWhiteKnight());
    assert (locations.contains(Location.fromHumanReadable("b1")));
    assert (locations.contains(Location.fromHumanReadable("g1")));

    locations = boardController.getBoardStatus().getLocationOf(PieceFactory.getBlackKnight());
    assert (locations.contains(Location.fromHumanReadable("b8")));
    assert (locations.contains(Location.fromHumanReadable("g8")));

    locations = boardController.getBoardStatus().getLocationOf(PieceFactory.getWhitePawn());
    for (int i = 1; i <= 8; i++) {
      char column = (char) (i + 'a' - 1);
      assert (locations.contains(Location.fromHumanReadable(column + "2")));
    }

    locations = boardController.getBoardStatus().getLocationOf(PieceFactory.getBlackPawn());
    for (int i = 1; i <= 8; i++) {
      char column = (char) (i + 'a' - 1);
      assert (locations.contains(Location.fromHumanReadable(column + "7")));
    }
  }

  @Test
  public void resetBorad() throws Exception {
    boardController.freshGame();
    assertEquals(boardController.getCurrentPieces().size(), 32);
    boardController.resetBorad();
    assertEquals(boardController.getCurrentPieces().size(), 0);
  }
}
