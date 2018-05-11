package de.tabit.chess.model;

import de.tabit.chess.model.Piece.Side;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BoardStatus {
    private HashMap<Piece, List<Location>> pieceLocationMAp ;
    private HashMap<Location, Piece> locationMap ;

  public BoardStatus(){
    pieceLocationMAp = new HashMap<>(12);
    //TODO initialize the map
    for(Side side: Side.values()){
      pieceLocationMAp.put(PieceFactory.getKing(side), new ArrayList<Location>());
      pieceLocationMAp.put(PieceFactory.getQueen(side), new ArrayList<Location>());
      pieceLocationMAp.put(PieceFactory.getCastle(side), new ArrayList<Location>());
      pieceLocationMAp.put(PieceFactory.getBishop(side), new ArrayList<Location>());
      pieceLocationMAp.put(PieceFactory.getKnight(side), new ArrayList<Location>());
      pieceLocationMAp.put(PieceFactory.getPawn(side), new ArrayList<Location>());
    }

    locationMap = new HashMap<>();
  }

  private BoardStatus(BoardStatus boardStatus){
    this();
    boardStatus.locationMap.entrySet().forEach(es->{
      Location location = new Location(es.getKey());
      Piece piece = new Piece(es.getValue());
      locationMap.put(location, piece);
      pieceLocationMAp.get(piece).add(location);
    });
  }

  public BoardStatus(List<PieceLocation> pieceLocations) {
    this();
    pieceLocations.stream().forEach(pl->{
      pieceLocationMAp.get(pl.getPiece()).add(pl.getLocation());
      locationMap.put(pl.getLocation(), pl.getPiece());
    });
  }


    public int getNumberOf(Piece piece){
        List<Location> locations = pieceLocationMAp.get(piece);
        return locations.size();
    }


    public BoardStatus union(PieceLocation pieceLocation) {
      BoardStatus newBoardStatus = new BoardStatus(this);
      addPieceToBoard(pieceLocation, newBoardStatus);
      return newBoardStatus ;
    }

  private void addPieceToBoard(PieceLocation pieceLocation, BoardStatus newBoardStatus) {
    Location location = pieceLocation.getLocation();
    Piece piece = pieceLocation.getPiece();
    if(piece!=null){
      List<Location> locations = newBoardStatus.pieceLocationMAp.get(pieceLocation.getPiece());
      locations.remove(location);
    }
    newBoardStatus.locationMap.put(location, piece);
    newBoardStatus.pieceLocationMAp.get(pieceLocation.getPiece()).add(location);
  }

  public BoardStatus union(List<PieceLocation> pieceLocations) {
    BoardStatus newBoardStatus = new BoardStatus(this);
    pieceLocations.forEach(pl->addPieceToBoard(pl, newBoardStatus));
    return newBoardStatus ;
  }

  public List<PieceLocation> getCurrentPieces() {
    return locationMap.entrySet().stream()
        .map(pp-> new PieceLocation(pp.getValue(), pp.getKey()))
        .collect(Collectors.toList());
  }

  public void makeMove(PieceLocation from, PieceLocation to) {
    pieceLocationMAp.get(from.getPiece()).remove(from.getLocation());
    pieceLocationMAp.get(to.getPiece()).remove(to.getLocation());
    pieceLocationMAp.get(to.getPiece()).add(to.getLocation());
    locationMap.remove(from.getLocation());
    locationMap.put(to.getLocation(), from.getPiece());
  }

  public List<Location> getLocationOf(Piece piece){
      return pieceLocationMAp.get(piece);
  }
}
