package de.tabit.chess.view;

import de.tabit.chess.model.Location;
import de.tabit.chess.model.Piece;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/** Created by e1528895 on 5/9/18. */
// @Data
public class ChessCell extends JLabel {

  private ImageIcon imageIcon = null;

  private Piece piece = null;
  private Location boardLocation;

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
      BufferedImage imageForChessPiece =
          PieceImageUtil.getBufferedImage(piece);
      imageIcon = new ImageIcon(imageForChessPiece);
      setImageIcon(imageIcon);
    }else
      setImageIcon(null);
  }

  public void setBoardLocation(Location boardLocation) {
    this.boardLocation = boardLocation;
  }

  public Location getBoardLocation() {
    return boardLocation;
  }
}
