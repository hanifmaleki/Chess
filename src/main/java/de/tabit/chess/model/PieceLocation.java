package de.tabit.chess.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** Created by Hanif on 5/9/18. This class is composed of a piece as well as its location. */
@ToString
@EqualsAndHashCode
public class PieceLocation {

  @Getter @Setter private Piece piece;

  @Getter @Setter private Location location;

  public PieceLocation(Piece piece, Location location) {
    this.piece = piece;
    this.location = location;
  }
}
