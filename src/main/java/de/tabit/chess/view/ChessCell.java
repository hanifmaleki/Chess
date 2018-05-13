package de.tabit.chess.view;

import de.tabit.chess.model.Location;
import de.tabit.chess.model.Piece;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import lombok.Getter;
import lombok.Setter;

/** Created by Hanif Maleki on 5/9/18. The view component of chells of the chess board */
public class ChessCell extends JLabel {

  @Getter private ImageIcon imageIcon = null;

  @Getter private Piece piece = null;
  @Getter @Setter private Location boardLocation;

  private void setImageIcon(ImageIcon imageIcon) {
    this.imageIcon = imageIcon;
    setIcon(imageIcon);
  }

  public void setPiece(Piece piece) {
    this.piece = piece;

    if (piece != null) {
      BufferedImage imageForChessPiece = PieceImageUtil.getBufferedImage(piece);
      imageIcon = new ImageIcon(imageForChessPiece);
      setImageIcon(imageIcon);
    } else setImageIcon(null);
  }
}
