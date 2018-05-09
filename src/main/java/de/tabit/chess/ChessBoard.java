package de.tabit.chess;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by e1528895 on 5/9/18.
 */
public class ChessBoard extends JPanel implements MouseListener,
    MouseMotionListener {

  public static final int BOARD_LENGTH = 8 ;
  public static final Color BG = new Color(0x056F13);

  private ChessCell cells[][] = new ChessCell[BOARD_LENGTH][BOARD_LENGTH];

  //private ImageIcon draggingIcon = null;
  private Piece draggingPiece = null;
  private ChessCell originCell = null;

  public ChessBoard() {
    GridLayout gridLayout = new GridLayout(BOARD_LENGTH,BOARD_LENGTH);

    setLayout(gridLayout);


    boolean colorTuggleVariable = true;
    for(int i = 0 ; i < BOARD_LENGTH ; i++)
      for(int j = 0 ; j < BOARD_LENGTH ; j++) {
        ChessCell label = new ChessCell();
        label.setOpaque(true);
        if(colorTuggleVariable)
          label.setBackground(Color.white);
        else
          label.setBackground(BG);
        if(j!=BOARD_LENGTH-1)
        colorTuggleVariable = ! colorTuggleVariable;
        label.setMinimumSize(new Dimension(PieceImageUtil.WIDTH, PieceImageUtil.WIDTH));
        label.setPreferredSize(new Dimension(PieceImageUtil.WIDTH, PieceImageUtil.WIDTH));
        cells[i][j]=label;
        add(label);
      }
      addMouseListener(this);
      addMouseMotionListener(this);

  }

  public void addPieceToBoard(Piece piece, int row, int column){
    ChessCell cell = cells[row][column];
    cell.setPiece(piece);
  }

  public void addPiecesToBoard(List<PiecePoint> piecePoints){
    piecePoints.forEach(p->addPieceToBoard(p.getPiece(),p.getPoint().x, p.getPoint().y));
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    Point point = e.getPoint();
    System.out.println(point);
    Component component = getComponentAt(point);
    if (component != null) System.out.println(component);
  }

  @Override
  public void mousePressed(MouseEvent e) {
    Point point = e.getPoint();
    System.out.println(point);
    Component component = getComponentAt(point);
    if (component == null){
      System.out.println("null");
      return ;
    }
    if(!(component instanceof  JLabel)){
      System.out.println("Not Jlabel");
      return ;
    }

    originCell = (ChessCell) component;
    //draggingIcon = originCell.getImageIcon();
    draggingPiece = originCell.getPiece();
    originCell.setPiece(null);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (originCell == null)
      return;
    Point point = e.getPoint();
    System.out.println(point);
    Component component = getComponentAt(point);
    if (component == null) {
      System.out.println("null");
      return;
    }
    if (!(component instanceof JLabel)) {

      System.out.println("Not Jlabel");
      return;
    }

    if (draggingPiece != null){
      ChessCell cell = (ChessCell) component;
      cell.setPiece(draggingPiece);
    }

    draggingPiece = null;
    originCell = null;
    setCursor(Cursor.getDefaultCursor());
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if(draggingPiece!=null)
      setCursor(Toolkit
        .getDefaultToolkit().createCustomCursor(draggingPiece.getImageIcon().getImage(),new Point(0,0),"custom cursor"));

  }

  @Override
  public void mouseMoved(MouseEvent e) {

  }
}
