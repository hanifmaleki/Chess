package de.tabit.chess.model;

import de.tabit.chess.model.Piece.Side;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Hanif Maleki This class is the main model of the library which maintains the status of
 * a chessboard containing the pieces and their location
 */
@Slf4j
public class BoardStatus {

  public static final int BOARD_LENGTH = 8;
  private HashMap<Piece, List<Location>> pieceLocationMAp;
  private HashMap<Location, Piece> locationMap;

  public BoardStatus() {
    pieceLocationMAp = new HashMap<>(12);
    for (Side side : Side.values()) {
      pieceLocationMAp.put(PieceFactory.getKing(side), new ArrayList<>());
      pieceLocationMAp.put(PieceFactory.getQueen(side), new ArrayList<>());
      pieceLocationMAp.put(PieceFactory.getCastle(side), new ArrayList<>());
      pieceLocationMAp.put(PieceFactory.getBishop(side), new ArrayList<>());
      pieceLocationMAp.put(PieceFactory.getKnight(side), new ArrayList<>());
      pieceLocationMAp.put(PieceFactory.getPawn(side), new ArrayList<>());
    }

    locationMap = new HashMap<>();
  }

  private BoardStatus(BoardStatus boardStatus) {
    this();
    boardStatus
        .locationMap
        .forEach((key, value) -> {
          Location location = new Location(key);
          Piece piece = new Piece(value);
          locationMap.put(location, piece);
          pieceLocationMAp.get(piece).add(location);
        });
  }

  public BoardStatus(List<PieceLocation> pieceLocations) {
    this();
    pieceLocations
        .forEach(
            pl -> {
              pieceLocationMAp.get(pl.getPiece()).add(pl.getLocation());
              locationMap.put(pl.getLocation(), pl.getPiece());
            });
  }

  public int getNumberOf(Piece piece) {
    List<Location> locations = pieceLocationMAp.get(piece);
    int size = locations.size();
    log.debug("There exist " + size + " number of " + piece + " in the board status");
    return size;
  }

  public BoardStatus union(PieceLocation pieceLocation) {
    BoardStatus newBoardStatus = new BoardStatus(this);
    addPieceToBoard(pieceLocation, newBoardStatus);
    return newBoardStatus;
  }

  private void addPieceToBoard(PieceLocation pieceLocation, BoardStatus newBoardStatus) {
    log.debug(
        "adding "
            + pieceLocation.getPiece()
            + " to the location "
            + Location.toHumanReadable(pieceLocation.getLocation()));
    Location location = pieceLocation.getLocation();
    Piece piece = pieceLocation.getPiece();
    if (piece != null) {
      List<Location> locations = newBoardStatus.pieceLocationMAp.get(pieceLocation.getPiece());
      locations.remove(location);
    }
    newBoardStatus.locationMap.put(location, piece);
    newBoardStatus.pieceLocationMAp.get(pieceLocation.getPiece()).add(location);
  }

  public BoardStatus union(List<PieceLocation> pieceLocations) {
    BoardStatus newBoardStatus = new BoardStatus(this);
    pieceLocations.forEach(pl -> addPieceToBoard(pl, newBoardStatus));
    return newBoardStatus;
  }

  public List<PieceLocation> getCurrentPieces() {
    return locationMap
        .entrySet()
        .stream()
        .map(pp -> new PieceLocation(pp.getValue(), pp.getKey()))
        .collect(Collectors.toList());
  }

  public void makeMove(PieceLocation from, PieceLocation to) {
    log.debug(
        "Moving "
            + from.getPiece()
            + " from "
            + Location.toHumanReadable(from.getLocation())
            + " to "
            + Location.toHumanReadable(to.getLocation()));
    pieceLocationMAp.get(from.getPiece()).remove(from.getLocation());
    pieceLocationMAp.get(to.getPiece()).remove(to.getLocation());
    pieceLocationMAp.get(to.getPiece()).add(to.getLocation());
    locationMap.remove(from.getLocation());
    locationMap.put(to.getLocation(), from.getPiece());
  }

  public List<Location> getLocationOf(Piece piece) {
    return pieceLocationMAp.get(piece);
  }
}
