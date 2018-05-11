package de.tabit.chess.view;

import de.tabit.chess.controller.BoardController;
import de.tabit.chess.model.BoardStatus;
import de.tabit.chess.model.Location;
import de.tabit.chess.model.Piece;
import de.tabit.chess.model.PieceLocation;
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
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Created by e1528895 on 5/9/18. */
// TODO sout to log

public class ChessBoard extends JPanel
    implements MouseListener, MouseMotionListener, BoardStatusChangeListener {

  //TODO move to a better place
  public static final int BOARD_LENGTH = 8;
  public static final Color BG = new Color(0x056F13);
  private final BoardController controller;

  HashMap<Location, ChessCell> cellMap = new HashMap<>(BOARD_LENGTH * BOARD_LENGTH);

  private Piece draggingPiece = null;
  private ChessCell originCell = null;

  public ChessBoard(BoardController controller) {
    this.controller = controller ;
    controller.setChangeListener(this);
    initCells();
  }

  private void initCells() {
    GridLayout gridLayout = new GridLayout(BOARD_LENGTH, BOARD_LENGTH);

    setLayout(gridLayout);

    boolean colorTuggleVariable = true;
    for (int i = 0; i < BOARD_LENGTH; i++)
      for (int j = 0; j < BOARD_LENGTH; j++) {
        ChessCell cell = new ChessCell();
        cell.setOpaque(true);
        if (colorTuggleVariable) cell.setBackground(Color.white);
        else cell.setBackground(BG);
        if (j != BOARD_LENGTH - 1) colorTuggleVariable = !colorTuggleVariable;
        cell.setMinimumSize(new Dimension(PieceImageUtil.WIDTH, PieceImageUtil.WIDTH));
        cell.setPreferredSize(new Dimension(PieceImageUtil.WIDTH, PieceImageUtil.WIDTH));
        // cells[i][j]=cell;
        Location location = new Location(i, j);
        cell.setBoardLocation(location);
        cellMap.put(location, cell);
        add(cell);
      }
    addMouseListener(this);
    addMouseMotionListener(this);
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
    if (component == null) {
      System.out.println("null");
      return;
    }
    if (!(component instanceof JLabel)) {
      System.out.println("Not Jlabel");
      return;
    }

    originCell = (ChessCell) component;
    draggingPiece = originCell.getPiece();
    originCell.setPiece(null);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (originCell == null) return;
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

    if (draggingPiece != null) {
      PieceLocation from = new PieceLocation(draggingPiece, originCell.getBoardLocation());
      ChessCell cell = (ChessCell) component;
      PieceLocation to = new PieceLocation(draggingPiece, cell.getBoardLocation());
      if(controller.makeAMove(from, to))
        cell.setPiece(draggingPiece);
      else
        originCell.setPiece(draggingPiece);
    }

    draggingPiece = null;
    originCell = null;
    setCursor(Cursor.getDefaultCursor());
  }

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mouseDragged(MouseEvent e) {
    if (draggingPiece != null) {
      BufferedImage image = PieceImageUtil.getBufferedImage(draggingPiece);
      setCursor(
          Toolkit.getDefaultToolkit()
              .createCustomCursor(
                  image, new Point(0, 0), "custom cursor"));
    }
  }


  @Override
  public void mouseMoved(MouseEvent e) {}

  @Override
  public void statusChanged(BoardStatus newBoardStatus) {
    clearCells();
    List<PieceLocation> currentPieces = newBoardStatus.getCurrentPieces();
    currentPieces.forEach(pl -> cellMap.get(pl.getLocation()).setPiece(pl.getPiece()));
  }

  private void clearCells() {
    cellMap.entrySet().forEach(es -> es.getValue().setPiece(null));
  }
}
