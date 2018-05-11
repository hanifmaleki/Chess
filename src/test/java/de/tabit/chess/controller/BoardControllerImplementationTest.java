package de.tabit.chess.controller;

import static org.junit.Assert.*;

import de.tabit.chess.model.Location;
import de.tabit.chess.model.Piece.Side;
import de.tabit.chess.model.PieceFactory;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by e1528895 on 5/11/18.
 */
public class BoardControllerImplementationTest {

  BoardController boardController = new BoardControllerImplementation();



  @Test
  public void freshGame() throws Exception {
    //TODO move to before
    boardController.setMoveValidator(new MoveValidatorImpl());
    boardController.setBoardValidator(new BoardValidatorImpl());



    boardController.freshGame();
    assertEquals(boardController.getCurrentPieces().size(), 32);
    for(Side side: Side.values()) {
      assertEquals(boardController.getBoardStatus().getNumberOf(PieceFactory.getKing(side)), 1);
      assertEquals(boardController.getBoardStatus().getNumberOf(PieceFactory.getQueen(side)), 1);
      assertEquals(boardController.getBoardStatus().getNumberOf(PieceFactory.getBishop(side)), 2);
      assertEquals(boardController.getBoardStatus().getNumberOf(PieceFactory.getKnight(side)), 2);
      assertEquals(boardController.getBoardStatus().getNumberOf(PieceFactory.getCastle(side)), 2);
    }

    List<Location> locations = boardController.getBoardStatus()
        .getLocationOf(PieceFactory.getWhiteKing());
    assertEquals(locations.get(0), Location.fromHumanReadable("e1"));
    locations = boardController.getBoardStatus()
        .getLocationOf(PieceFactory.getBlackKing());
    assertEquals(locations.get(0), Location.fromHumanReadable("e8"));

    locations = boardController.getBoardStatus()
        .getLocationOf(PieceFactory.getWhiteQueen());
    assertEquals(locations.get(0), Location.fromHumanReadable("d1"));
    locations = boardController.getBoardStatus()
        .getLocationOf(PieceFactory.getBlackQueen());
    assertEquals(locations.get(0), Location.fromHumanReadable("d8"));

    locations = boardController.getBoardStatus()
        .getLocationOf(PieceFactory.getWhiteCastle());
    assert(locations.contains(Location.fromHumanReadable("a1")));
    assert(locations.contains(Location.fromHumanReadable("h1")));

    locations = boardController.getBoardStatus()
        .getLocationOf(PieceFactory.getBlackCastle());
    assert(locations.contains(Location.fromHumanReadable("a8")));
    assert(locations.contains(Location.fromHumanReadable("h8")));

    //TODO complete test with all pieces
  }

  @Test
  public void resetBorad() throws Exception {
  }
}