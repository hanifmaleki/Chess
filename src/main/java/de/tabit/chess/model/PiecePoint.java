package de.tabit.chess.model;

import java.awt.Point;

/**
 * Created by e1528895 on 5/9/18.
 */
public class PiecePoint {

  private final Piece piece;

  private final Point point ;

  public PiecePoint(Piece piece, Point point) {
    this.piece = piece;
    this.point = point;
  }

  public Piece getPiece() {
    return piece;
  }

  public Point getPoint() {
    return point;
  }
}
