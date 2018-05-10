package de.tabit.chess.view;

import de.tabit.chess.model.Piece;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/** Created by e1528895 on 5/9/18. */
// @Data
public class ChessCell extends JLabel {

  private ImageIcon imageIcon = null;

  private Piece piece = null;

  public ImageIcon getImageIcon() {
    return imageIcon;
  }

  private void setImageIcon(ImageIcon imageIcon) {
    this.imageIcon = imageIcon;
    setIcon(imageIcon);
  }

  public Piece getPiece() {
    return piece;
  }

  public void setPiece(Piece piece) {
    this.piece = piece;
    if(piece!=null) {
      setImageIcon(piece.getImageIcon());
    }else
      setImageIcon(null);
  }
}
