package de.tabit.chess.model;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import de.tabit.chess.view.PieceImageUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by Hanif Maleki on 5/9/18.
 * This model represents a piece containing side(white, black)
 * And name(king, queen, castle, bishop, knight, pawn)
 */
@ToString
@EqualsAndHashCode
public class Piece {

  public enum Name{KING(0), QUEEN(1), CASTLE(2), BISHOP(3), KNIGHT(4), PAWN(5);
    private final int value;
    Name(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

  public enum Side{WHITE(0), BLACK(1);
    private final int value;
    Side(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

  @Getter
  private final Name name;

  @Getter private final Side side;

  public Piece(Name name, Side side) {
    this.name = name;
    this.side = side;
  }

  public Piece(Piece piece) {
    this(piece.name , piece.side);
  }

}
