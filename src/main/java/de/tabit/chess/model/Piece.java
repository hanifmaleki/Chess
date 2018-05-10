package de.tabit.chess.model;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import de.tabit.chess.view.PieceImageUtil;

/**
 * Created by e1528895 on 5/9/18.
 */
public class Piece {
  public enum Name{KING(0), QUEEN(1), CASTLE(2), BISHOP(3), KNIGHT(4), PAWN(5);
    private final int value;
    private Name(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

  public enum Side{WHITE(0), BLACK(1);
    private final int value;
    private Side(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

  private final Name name;

  private final Side side;

  private final ImageIcon imageIcon ;

  //private final String symbol;

  public Piece(Name name, Side side) {
    this.name = name;
    this.side = side;
    BufferedImage imageForChessPiece =
        PieceImageUtil.getImageForChessPiece(
            name.getValue(), side.getValue(), true);
    imageIcon = new ImageIcon(imageForChessPiece);
  }

  public Name getName() {
    return name;
  }

  public Side getSide() {
    return side;
  }

  public ImageIcon getImageIcon() {
    return imageIcon;
  }

  public static String getSymbol(Name name, Side side) {
    return null;
  }

  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof Piece))
      return false;
    Piece piece = (Piece) obj;
    if(piece.getName()!=name)
      return false;
    if(piece.getSide()!=side)
      return false ;
    return true;
  }
}
