package de.tabit.chess.model;

import java.awt.Point;

/**
 * Created by e1528895 on 5/9/18.
 */
public class PieceLocation {

  private Piece piece;

  private Location location ;

  public PieceLocation(Piece piece, Location location) {
    this.piece = piece;
    this.location = location;
  }

  public Piece getPiece() {
    return piece;
  }

  public void setPiece(Piece piece) {
    this.piece = piece;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

}
